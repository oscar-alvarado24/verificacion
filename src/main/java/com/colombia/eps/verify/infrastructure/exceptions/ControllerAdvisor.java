package com.colombia.eps.verify.infrastructure.exceptions;

import com.colombia.eps.verify.application.dto.RequestResponse;
import com.colombia.eps.verify.application.exception.DontGenerateCodeException;
import com.colombia.eps.verify.common.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NotSendMessageException.class)
    public ResponseEntity<RequestResponse<String>>notSendMessage(NotSendMessageException notSendMessageException) {
        RequestResponse<String> body = new RequestResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), notSendMessageException.getMessage(), Constants.NOT_SEND_MESSAGE_BODY);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(NotGenerateSessionException.class)
    public ResponseEntity<RequestResponse<String>> notGenerateSession(NotGenerateSessionException notGenerateSessionException) {
        RequestResponse<String> body = new RequestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), notGenerateSessionException.getMessage(), Constants.NOT_GENERATE_SESSION_BODY);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<RequestResponse<String>> validateFailed(ValidationFailedException validationFailedException) {
        RequestResponse<String> body = new RequestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), validationFailedException.getMessage(), Constants.VALIDATE_FAILED);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
    @ExceptionHandler(DontGenerateCodeException.class)
    public ResponseEntity<RequestResponse<Map<String,String>>> dontGenerateCode(DontGenerateCodeException dontGenerateCodeException) {
        Map<String,String> data = new HashMap<>();
        data.put(Constants.BODY,Constants.EMPTY);
        data.put(Constants.EXCEPTION_NAME,dontGenerateCodeException.getClass().getSimpleName());
        RequestResponse<Map<String,String>> body = new RequestResponse<>(HttpStatus.BAD_REQUEST.value(), dontGenerateCodeException.getMessage(), data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}


