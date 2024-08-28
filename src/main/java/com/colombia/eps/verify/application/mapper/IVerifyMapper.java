package com.colombia.eps.verify.application.mapper;

import com.colombia.eps.verify.application.dto.CodeRequest;
import com.colombia.eps.verify.application.dto.RequestResponse;
import com.colombia.eps.verify.common.util.Constants;
import com.colombia.eps.verify.domain.model.CodeValidate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Map;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVerifyMapper {

    default RequestResponse<String> toRequestResponse(Map<String,String> verifyDomain) {
        RequestResponse<String> response = new RequestResponse<>();
        response.setStatus(Integer.parseInt(verifyDomain.get(Constants.STATUS)));
        response.setMessage(verifyDomain.get(Constants.MESSAGE));
        response.setBody(verifyDomain.get(Constants.BODY));
        return response;
    }

    default CodeValidate toCode(CodeRequest codeRequest, String patientNumber){
        return new CodeValidate(patientNumber, codeRequest.getCode());
    }
}