package com.colombia.eps.verify.domain.spi;

import com.colombia.eps.verify.infrastructure.output.twilio.entity.CodeValidateEntity;

import java.util.Map;

public interface IVerifyPersistencePort {
    Map<String,String> validateCode (CodeValidateEntity codeValidate);
}