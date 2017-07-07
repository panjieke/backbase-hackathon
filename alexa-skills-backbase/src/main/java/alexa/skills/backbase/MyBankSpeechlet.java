package alexa.skills.backbase;

import alexa.skills.backbase.lib.RestClient;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBankSpeechlet implements SpeechletV2 {

    private static final Logger log = LoggerFactory.getLogger(MyBankSpeechlet.class);
    private final RestClient restClient = new RestClient();
    private static final String MY_BANK_CART = "MyBank";
    private static final String STOP_INTENT = "AMAZON.StopIntent";
    private static final String NO_INTENT = "AMAZON.NoIntent";

    private static final String YES_INTENT = "AMAZON.YesIntent";
    private static final String STATUS_INTENT = "GetStatus";
    private static final String PAYMENT_REQUEST_INTENT = "PaymentRequest";
    private static final String TRANSFER_INTENT = "Transfer";
    private static final String CREDIT_CONFIRMATION_INTENT = "CreditConfirmation";

    private static final String PERSON_NAME_SLOT = "PersonName";
    private static final String AMOUNT_SLOT = "Amount";
    private static final String MONEY_SLOT = "Money";
    private static final String CREDIT_SLOT = "CreditName";

    private static final String WELCOME_MESSAGE = "Hi %s, Welcome to My Bank.";
    private static final String GOODBYE_MESSAGE = "Thank you and enjoy your day.";
    private static final String ACCOUNT_BALANCE_MESSAGE = "You have 1000 euros in your debit account.";
    private static final String PAYMENT_REQUEST_MESSAGE = "The payment has been sent to %s";
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

        log.info("onIntent requestId={}, sessionId={}",
                request.getRequestId(),
                speechletRequestEnvelope.getSession().getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : new String();
        switch (intentName) {
            case STOP_INTENT:
            case NO_INTENT:
            case YES_INTENT:
                return getGoodByeResponse();
            case STATUS_INTENT:
                return getStatusResponse();
            case PAYMENT_REQUEST_INTENT: {
                return sendPaymentRequest(intent.getSlot(PERSON_NAME_SLOT).getValue());
            }
            case TRANSFER_INTENT: {
                return sendTransferRequest(intent.getSlot(AMOUNT_SLOT).getValue(), intent.getSlot(MONEY_SLOT).getValue());
            }
            case CREDIT_CONFIRMATION_INTENT: {
                return getCreditConfirmation(intent.getSlot(CREDIT_SLOT).getValue());
            }
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
        String userName = null;

        try {
            String token = restClient.login();
            userName = restClient.getUserName(token);
            if (userName == null || userName.isEmpty()) {
                userName = "";
            }
        } catch (Exception e) {
            userName = "";
        }


        return createSimpleResponse(String.format(WELCOME_MESSAGE, userName));
    }

    private SpeechletResponse getGoodByeResponse() {
        return createSimpleResponse(GOODBYE_MESSAGE);
    }

    private SpeechletResponse getStatusResponse() {
        return createSimpleResponse(ACCOUNT_BALANCE_MESSAGE);
    }

    private SpeechletResponse sendPaymentRequest(String contact) {
        String message = String.format(PAYMENT_REQUEST_MESSAGE, contact);
        return createSimpleResponse(message);
    }

    private SpeechletResponse sendTransferRequest(String amount, String money) {
        String message = String.format("%s %s has been transferred.", amount, money);
        return createSimpleResponse(message);
    }

    private SpeechletResponse getCreditConfirmation(String creditName) {
        String message = String.format("%s has been transferred.", creditName);
        return createSimpleResponse(message);
    }

    private SpeechletResponse getUnknownCommandResponse() {
        return createSimpleResponse(UNKNOWN_MESSAGE);
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

    private SimpleCard createCard(String title, String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(speechText);

        return card;
    }
}
