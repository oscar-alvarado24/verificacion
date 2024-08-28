package com.colombia.eps.verify.infrastructure.output.twilio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeValidateEntity {
    private String cellPhone;
    private String code;
}