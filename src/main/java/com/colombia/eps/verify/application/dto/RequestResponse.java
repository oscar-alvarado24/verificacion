package com.colombia.eps.verify.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse<T> {
    private int status;
    private String message;
    private T body;
}
