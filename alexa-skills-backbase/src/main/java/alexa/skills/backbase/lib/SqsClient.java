package alexa.skills.backbase.lib;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class SqsClient {
    private final BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAI3Q2D7PQMZSBA3IA", "asEaLcaXxCeGzht1kMZLmkdIFQjgLsu5m8lskRNN");
    private final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    private String queueUrl;

    public SqsClient(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    public Message readMessage() {
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        if (messages.size() > 0) {
            return messages.get(0);
        }

        return null;
    }
}
