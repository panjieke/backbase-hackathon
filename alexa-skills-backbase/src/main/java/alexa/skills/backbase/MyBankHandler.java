package alexa.skills.backbase;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class MyBankHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        String appId = System.getenv("APP_ID");
        supportedApplicationIds.add(appId);
    }

    public MyBankHandler() {
        super(new MyBankSpeechlet(), supportedApplicationIds);
    }
}
