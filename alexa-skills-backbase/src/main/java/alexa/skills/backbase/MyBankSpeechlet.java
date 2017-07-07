package alexa.skills.backbase;

import alexa.skills.backbase.lib.RestClient;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioItem;
import com.amazon.speech.speechlet.interfaces.audioplayer.PlayBehavior;
import com.amazon.speech.speechlet.interfaces.audioplayer.Stream;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.PlayDirective;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MyBankSpeechlet implements SpeechletV2 {

    private static final Logger log = LoggerFactory.getLogger(MyBankSpeechlet.class);
    private final RestClient restClient = new RestClient();
    private static final String MY_BANK_CART = "MyBank";
    private static final String STOP_INTENT = "AMAZON.StopIntent";
    private static final String NO_INTENT = "AMAZON.NoIntent";
    private static final String YES_INTENT = "AMAZON.YesIntent";

    private static final String STATUS_INTENT = "GetStatus";
    private static final String TRANSFER_INTENT = "Transfer";

    private static final String PERSON_NAME_SLOT = "PersonName";
    private static final String DIRECTION_SLOT = "Direction";

    private static final String WELCOME_MESSAGE = "Hi Rik, I hope everything is alright, you seem to be short on funds lately. Anything I should know about?";
    private static final String GOODBYE_MESSAGE = "Thank you and enjoy your day.";
    private static final String ACCOUNT_BALANCE_MESSAGE = "You have 1000 euros in your debit account.";
    private static final String TRANSFER_MESSAGE = "okay done, don't forget you still owe Lisa 20 euros for those drinks last Friday";
    private static final String ANOTHER_TRANSFER_MESSAGE = "done, your balance is now 450 euro's. Anything else I can do for you today?";
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
            case TRANSFER_INTENT:
                return sendTransferRequest(intent);
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
        return createRepromptResponse(WELCOME_MESSAGE, "");
    }

    private SpeechletResponse getGoodByeResponse() {
        return createSimpleResponse(GOODBYE_MESSAGE);
    }

    private SpeechletResponse getStatusResponse() {
        return createSimpleSsmlResponse(ACCOUNT_BALANCE_MESSAGE);
    }

    private SpeechletResponse sendTransferRequest(Intent intent) {
        String direction = intent.getSlot(DIRECTION_SLOT).getValue();
        String person = intent.getSlot(PERSON_NAME_SLOT).getValue();
        log.info("Direction: " + direction + " Person: " + person);

        if ("from".equals(direction) && person.toLowerCase().contains("account")) {
            return createRepromptResponse(TRANSFER_MESSAGE, "");
        }

        return createRepromptResponse(ANOTHER_TRANSFER_MESSAGE, "");
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

    private SpeechletResponse createSimpleSsmlResponse(String speechText) {
        SsmlOutputSpeech output = new SsmlOutputSpeech();
        output.setSsml("<speak> " + speechText + "<audio src='https://s3-eu-west-1.amazonaws.com/tempmediafile/alexa.mp3'/> " + " </speak>");

        return SpeechletResponse.newTellResponse(output, createCard(MY_BANK_CART, speechText));
    }

    private SpeechletResponse createSimpleDirectiveResponse(String speechText, String url) {
        SimpleCard card = createCard(MY_BANK_CART, speechText);
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        PlayDirective directive = new PlayDirective();
        AudioItem audioItem = new AudioItem();
        Stream stream = new Stream();
        stream.setUrl(url);
        audioItem.setStream(stream);
        directive.setAudioItem(audioItem);
        directive.setPlayBehavior(PlayBehavior.REPLACE_ALL);

        SpeechletResponse response = SpeechletResponse.newTellResponse(speech, card);
        response.setDirectives(Arrays.asList(directive));

        return response;
    }

    private SimpleCard createCard(String title, String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(speechText);

        return card;
    }
}
