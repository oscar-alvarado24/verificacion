package com.colombia.eps.verify.infrastructure.exceptions;

public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(String message){
        super(message);
    }
}
