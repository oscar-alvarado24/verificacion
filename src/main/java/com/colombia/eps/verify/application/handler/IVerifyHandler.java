package com.colombia.eps.verify.application.handler;

import com.colombia.eps.verify.application.dto.CodeRequest;
import com.colombia.eps.verify.application.dto.EmailRequest;
import com.colombia.eps.verify.application.dto.RequestResponse;

public interface IVerifyHandler {
    RequestResponse<String> sendCode(EmailRequest emailRequest);
    RequestResponse <String> validateCode(CodeRequest codeRequest);
    void clearCellPhone();
}
