package alexa.skills.backbase;

import alexa.skills.backbase.lib.SqsClient;
import com.amazonaws.services.sqs.model.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SqsClientTest {

    @Test
    public void readMessage() throws Exception {
        whenReadMessage();
        thenTheMessageIsReturned("Test");
    }

    @Before
    public void setup() {
        sqsClient = new SqsClient("https://sqs.eu-west-1.amazonaws.com/473293451041/synch");
    }

    private void thenTheMessageIsReturned(String expectedContent) {
        assertNotNull(message);
        assertEquals(expectedContent, message.getBody());
    }

    private void whenReadMessage() {
        message = sqsClient.readMessage();
    }

    private SqsClient sqsClient;
    private Message message;
}
