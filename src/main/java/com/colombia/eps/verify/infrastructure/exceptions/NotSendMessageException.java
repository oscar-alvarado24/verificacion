package com.colombia.eps.verify.infrastructure.exceptions;

public class NotSendMessageException extends RuntimeException{
    public NotSendMessageException(String message){
        super(message);
    }
}