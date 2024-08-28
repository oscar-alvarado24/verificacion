package com.colombia.eps.verify.infrastructure.output.twilio.adapter;

import com.colombia.eps.verify.common.util.Constants;
import com.colombia.eps.verify.domain.spi.IVerifyPersistencePort;
import com.colombia.eps.verify.infrastructure.exceptions.ValidationFailedException;
import com.colombia.eps.verify.infrastructure.output.twilio.entity.CodeValidateEntity;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class TwilioAdapter implements IVerifyPersistencePort {

    public final String accountSid;
    public final String authToken;
    public final String serviceSid;

    /**
     * @param codeValidate cellphone and code to validate
     * @return response service
     */
    @Override
    public Map<String, String> validateCode(CodeValidateEntity codeValidate) {
        Map<String, String> response = new HashMap<>();
        try {
            Twilio.init(accountSid, authToken);
            VerificationCheck verificationCheck =
                    VerificationCheck.creator(serviceSid)
                            .setTo(Constants.COLOMBIAN_INDICATIVE.concat(codeValidate.getCellPhone()))
                            .setCode(codeValidate.getCode())
                            .create();
            log.info(Constants.MESSAGE_OK + "{}", verificationCheck.getSid());
            response.put(Constants.STATUS, Constants.CODE_200);
            response.put(Constants.MESSAGE, Constants.VALIDATE_SUSESFULL);
            response.put(Constants.BODY, verificationCheck.getStatus());
            return response;
        } catch (Exception e) {
            log.error(Constants.ERROR_MESSAGE + "{}", e.getMessage());
            throw new ValidationFailedException(Constants.VALIDATE_FAILED);
        }
    }
}