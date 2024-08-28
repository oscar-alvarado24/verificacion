package com.colombia.eps.verify.domain.usecase;

import com.colombia.eps.verify.domain.api.IVerifyServicePort;
import com.colombia.eps.verify.domain.model.CodeValidate;
import com.colombia.eps.verify.domain.spi.IVerifyPersistencePort;
import com.colombia.eps.verify.infrastructure.output.twilio.mapper.ITwilioMapper;

import java.util.Map;

public class VerifyUsecase implements IVerifyServicePort {

    private final IVerifyPersistencePort verifyPersistencePort;
    private final ITwilioMapper twilioMapper;

    public VerifyUsecase(IVerifyPersistencePort verifyPersistencePort, ITwilioMapper twilioMapper) {
        this.verifyPersistencePort = verifyPersistencePort;
        this.twilioMapper = twilioMapper;
    }

    /**
     * @param codeValidate code to validate and patient cellphone
     * @return twilio service response
     */
    @Override
    public Map<String, String> validateCode(CodeValidate codeValidate) {
        return verifyPersistencePort.validateCode(twilioMapper.toCodeValidateInfra(codeValidate));
    }
}
