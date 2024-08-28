package com.colombia.eps.verify.infrastructure.output.aws.util;

import com.colombia.eps.verify.common.util.Constants;
import com.colombia.eps.verify.infrastructure.exceptions.NotGenerateSessionException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;
import software.amazon.awssdk.services.sts.model.StsException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateSession {
    public static StaticCredentialsProvider generateSession(String roleArn, String roleSessionName) {

        Credentials tempRoleCredentials;
        AwsCredentialsProvider credentialsProvider = EnvironmentVariableCredentialsProvider.create();
        try (StsClient stsClient = StsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build()) {
            AssumeRoleRequest roleRequest = AssumeRoleRequest.builder().roleArn(roleArn).roleSessionName(roleSessionName).build();

            AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
            tempRoleCredentials = roleResponse.credentials();

            // Use the following temporary credential items for the client.
            String key = tempRoleCredentials.accessKeyId();
            String secKey = tempRoleCredentials.secretAccessKey();
            String secToken = tempRoleCredentials.sessionToken();

            return StaticCredentialsProvider.create(AwsSessionCredentials.create(key, secKey, secToken));
        } catch (StsException exception) {
            log.error(exception.getMessage());
            throw new NotGenerateSessionException(Constants.ERROR_MESSAGE_SERVICE);
        }
    }
}
