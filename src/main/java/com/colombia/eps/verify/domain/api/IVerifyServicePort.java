package com.colombia.eps.verify.domain.api;

import com.colombia.eps.verify.domain.model.CodeValidate;

import java.util.Map;

public interface IVerifyServicePort {
    Map<String,String> validateCode(CodeValidate codeValidate);
}