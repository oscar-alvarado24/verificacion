package com.colombia.eps.verify.infrastructure.output.twilio.mapper;

import com.colombia.eps.verify.domain.model.CodeValidate;
import com.colombia.eps.verify.infrastructure.output.twilio.entity.CodeValidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITwilioMapper {

    default CodeValidateEntity toCodeValidateInfra (CodeValidate codeValidate){
        CodeValidateEntity codeValidateEntity = new CodeValidateEntity();
        codeValidateEntity.setCode(codeValidate.getCode());
        codeValidateEntity.setCellPhone(codeValidate.getCellPhone());
        return codeValidateEntity;
    }
}