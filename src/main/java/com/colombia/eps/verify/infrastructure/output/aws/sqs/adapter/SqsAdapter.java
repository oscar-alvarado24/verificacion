package com.colombia.eps.verify.infrastructure.output.aws.sqs.adapter;

import com.colombia.eps.verify.domain.spi.ISqsPersistencePort;
import com.colombia.eps.verify.infrastructure.exceptions.NotSendMessageException;
import com.colombia.eps.verify.infrastructure.output.aws.util.GenerateSession;
import com.colombia.eps.verify.common.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Slf4j
public class SqsAdapter implements ISqsPersistencePort {

    @Value("${amazon.aws.sqsrole}")
    private String sqsRole;
    /**
     * @param telephone patient number to verify
     * @return response of service
     */
    @Override
    public Map<String, String> sendMessage(String telephone, String queueUrl) {
        Map <String, String> response = new HashMap<>();
        StaticCredentialsProvider credential = GenerateSession.generateSession(sqsRole, Constants.ROLE_SESSION_NAME_SQS);
        try(SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credential)
                .build()) {
            sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(telephone)
                    .build());
            response.put(Constants.STATUS, Constants.CODE_200);
            response.put(Constants.MESSAGE,Constants.SEND_MESSAGE_SUSESFULL);
            return response;
        }catch (SqsException exception) {
            log.error(exception.getMessage());
            throw new NotSendMessageException(Constants.ERROR_MESSAGE_SERVICE);
        }
    }

}