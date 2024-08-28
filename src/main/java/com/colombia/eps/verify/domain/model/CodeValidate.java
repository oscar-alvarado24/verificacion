package com.colombia.eps.verify.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeValidate {
    private String cellPhone;
    private String code;
}