package alexa.skills.backbase;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SMSClientTest {

//    @Test
    public void sendSMS() {
        String message = "Rik, Check if you get this push message.";
        String phoneNumber = "+31611354549";
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        sendSMSMessage(client, message, phoneNumber, smsAttributes);
    }

    @Before
    public void setup() {
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAIJ6QB5V7SROB33SQ", "RxoQYncAV5u90vhXR3VQ4zLSPn7f6rMVbVfjZzb1");
        client = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    public void sendSMSMessage(AmazonSNS snsClient, String message, String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        System.out.println(result);
    }

    private AmazonSNS client;
}
