package com.colombia.eps.verify.infrastructure.exceptions;

import com.colombia.eps.verify.application.dto.RequestResponse;
import com.colombia.eps.verify.common.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class ControllerAdvisor {

    @ExceptionHandler(NotGenerateSessionException.class)
    public RequestResponse<String> notGenerateSession(NotGenerateSessionException notGenerateSessionException) {
        return new RequestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), notGenerateSessionException.getMessage(), Constants.NOT_GENERATE_SESSION_BODY);
    }

    @ExceptionHandler(NotSendMessageException.class)
    public RequestResponse<String> notSendMessage(NotSendMessageException notSendMessageException) {
        return new RequestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), notSendMessageException.getMessage(), Constants.NOT_SEND_MESSAGE_BODY);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public RequestResponse<String> validateFailed(ValidationFailedException validationFailedException) {
        return new RequestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), validationFailedException.getMessage(), Constants.EMPTY);
    }
}