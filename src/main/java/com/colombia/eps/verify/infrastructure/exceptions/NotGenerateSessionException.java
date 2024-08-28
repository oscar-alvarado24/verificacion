package com.colombia.eps.verify.infrastructure.exceptions;

public class NotGenerateSessionException extends RuntimeException{
    public NotGenerateSessionException(String message){
        super(message);
    }
}