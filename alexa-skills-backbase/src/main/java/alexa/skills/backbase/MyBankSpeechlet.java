package alexa.skills.backbase;

import alexa.skills.backbase.lib.RestClient;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyBankSpeechlet implements SpeechletV2 {

    private static final Logger log = LoggerFactory.getLogger(MyBankSpeechlet.class);
    public static final String TAN_VALUE = "TanValue";
    private final RestClient restClient = new RestClient();
    private AmazonSNS client;
    private String realTan;

    private static final String MESSAGE = "Rik, This is your one time transaction tan: ";
    private static final String PHONE_NUMBER = "+31611354549";

    private static final String BANK_ID = "test-bank";
    private static final String ACCOUNT_ID = "RR-000001";
    private static final String VIEW_ID = "owner";
    private static final String OTHER_BANK_ID = "test-bank";
    private static final String OTHER_ACCOUNT_ID = "10800111111";

    private static final String SAVING_ACCOUNT_ID = "10800111111";
    private static final String MY_BANK_CART = "MyBank";
    private static final String STOP_INTENT = "AMAZON.StopIntent";
    private static final String NO_INTENT = "AMAZON.NoIntent";
    private static final String YES_INTENT = "AMAZON.YesIntent";
    private static final String STATUS_INTENT = "GetStatus";
    private static final String TAN_INTENT = "Tan";
    private static final String TRANSFER_INTENT = "Transfer";

    private static final String PERSON_NAME = "PersonName";

    private static final String DIRECTION_SLOT = "Direction";
    private static final String WELCOME_MESSAGE = "Hi Rik, I hope everything is alright, you seem to be short on funds lately. Anything I should know about?";
    private static final String GOODBYE_MESSAGE = "You are welcome, I hope you win today!!!";
    private static final String ACCOUNT_BALANCE_MESSAGE = "Well, um, unfortunately it's currently %s, Let me play some soothing music for you.";
    private static final String TRANSFER_MESSAGE = "okay I transferred 1000 dollars from my savings account, don't forget you still owe Lisa 20 dollars for those drinks last Friday";
    private static final String ANOTHER_TRANSFER_MESSAGE = "done, I transferred 20 dollars to Lisa, your balance is now %s. Anything else I can do for you today?";
    private static final String CONFIRM_TAN_MESSAGE = "Sure, you should receive a onetime passcode, can you read that out to me?";
    private static final String WRONG_TAN_MESSAGE = "Sorry, wrong code, please try it again.";
    private static final String UNKNOWN_MESSAGE = "Sorry, I could not understand your question.";


    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {
        log.info("onSessionStarted requestId={}, sessionId={}",
                speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        log.info("onLaunch requestId={}, sessionId={}",
                speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId());

        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {
        IntentRequest request = speechletRequestEnvelope.getRequest();
        Session session = speechletRequestEnvelope.getSession();

        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : new String();

        switch (intentName) {
            case STOP_INTENT:
            case NO_INTENT:
            case YES_INTENT:
                return getGoodByeResponse();
            case STATUS_INTENT:
                return getStatusResponse();
            case TRANSFER_INTENT:
                return sendTransferRequest(intent, session);
            case TAN_INTENT:
                return confirmTan(intent, session);
            default:
                return getUnknownCommandResponse();
        }
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {
        log.info("onSessionEnded requestId={}, sessionId={}",
                speechletRequestEnvelope.getRequest().getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId());
    }

    private SpeechletResponse getWelcomeResponse() {
        String name = restClient.getAccountDisplayName(BANK_ID, ACCOUNT_ID, VIEW_ID);

        return createRepromptResponse(String.format(WELCOME_MESSAGE, name), "");
    }

    private SpeechletResponse getGoodByeResponse() {
        return createSimpleResponse(GOODBYE_MESSAGE);
    }

    private SpeechletResponse getStatusResponse() {
        String balance = restClient.getAccountBalance(BANK_ID, ACCOUNT_ID, VIEW_ID);

        return createSimpleSsmlResponse(String.format(ACCOUNT_BALANCE_MESSAGE, balance));
    }

    private SpeechletResponse sendTransferRequest(Intent intent, Session session) {
        String direction = intent.getSlot(DIRECTION_SLOT).getValue();
        String person = intent.getSlot(PERSON_NAME).getValue();
        log.info("Direction: " + direction + " Person: " + person);

        sendTan();

        if (person.toLowerCase().contains("account")) {
            restClient.transferAmount(BANK_ID, SAVING_ACCOUNT_ID, VIEW_ID, BANK_ID, ACCOUNT_ID, 1000);
            session.setAttribute(PERSON_NAME, "account");
            return getBalanceSpeechletResponse(TRANSFER_MESSAGE);
        } else {
//            restClient.transferAmount(BANK_ID, ACCOUNT_ID, VIEW_ID, OTHER_BANK_ID, OTHER_ACCOUNT_ID, 20);
//            session.setAttribute(PERSON_NAME, "Lisa");
//            return getBalanceSpeechletResponse(ANOTHER_TRANSFER_MESSAGE);
            session.setAttribute(PERSON_NAME, "Lisa");
        }

        return getConfirmSpeechletResponse();
    }

    private SpeechletResponse confirmTan(Intent intent, Session session) {
        String tan = intent.getSlot(TAN_VALUE).getValue();
        String name = (String) session.getAttribute(PERSON_NAME);
        log.info("Name: " + name + " Tan: " + tan + " RealTan: " + realTan);

        if (tan != null && !tan.equals("1234")) {
            if (name.equals("account")) {
                session.setAttribute(PERSON_NAME, "account");
                return getBalanceSpeechletResponse(TRANSFER_MESSAGE);
            } else {
                restClient.transferAmount(BANK_ID, ACCOUNT_ID, VIEW_ID, OTHER_BANK_ID, OTHER_ACCOUNT_ID, 20);
                session.setAttribute(PERSON_NAME, "Lisa");
                return getBalanceSpeechletResponse(ANOTHER_TRANSFER_MESSAGE);
            }
        }

        return getReconfirmTanSpeechletResponse(WRONG_TAN_MESSAGE);
    }

    private SpeechletResponse getConfirmSpeechletResponse() {
        return createRepromptResponse(CONFIRM_TAN_MESSAGE, CONFIRM_TAN_MESSAGE);
    }

    private SpeechletResponse getBalanceSpeechletResponse(String text) {
        String balance = restClient.getAccountBalance(BANK_ID, ACCOUNT_ID, VIEW_ID);
        return createRepromptResponse(String.format(text, balance), "");
    }

    private SpeechletResponse getUnknownCommandResponse() {
        return createSimpleResponse(UNKNOWN_MESSAGE);
    }

    private SpeechletResponse getReconfirmTanSpeechletResponse(String message) {
        return createRepromptResponse(message, message);
    }

    private SpeechletResponse createSimpleResponse(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, createCard(MY_BANK_CART, speechText));
    }

    private SpeechletResponse createRepromptResponse(String speechText, String repromptText) {
        SimpleCard card = createCard(MY_BANK_CART, speechText);
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse createSimpleSsmlResponse(String speechText) {
        SsmlOutputSpeech output = new SsmlOutputSpeech();
        output.setSsml("<speak> " + speechText + "<audio src='https://s3-eu-west-1.amazonaws.com/tempmediafile/alexa.mp3'/> " + " </speak>");

        return SpeechletResponse.newTellResponse(output, createCard(MY_BANK_CART, speechText));
    }

    private SimpleCard createCard(String title, String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(speechText);

        return card;
    }

    private void sendTan() {
        try {
            generateRealTan();
            Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
            sendSMSMessage(MESSAGE + realTan, PHONE_NUMBER, smsAttributes);
        } catch (Exception e) {
            log.info("SendTan failed: ", e);
        }
    }

    private void generateRealTan() {
        Random random = new Random();
        realTan = String.format("%04d%n", random.nextInt(10));
        log.info("Real Tan: " + realTan);
    }

    private void sendSMSMessage(String message, String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        log.info("Message: " + message);
        AWSCredentials awsCredentials = new BasicAWSCredentials(System.getenv("ACCESS_KEY"), System.getenv("SECRET_KEY"));
        client = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
        PublishResult result = client.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        log.info("Sms sent with id: " + result.getMessageId());
    }
}
