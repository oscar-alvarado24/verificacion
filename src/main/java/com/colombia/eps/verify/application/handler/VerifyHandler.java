package com.colombia.eps.verify.application.handler;

import com.colombia.eps.verify.application.dto.CodeRequest;
import com.colombia.eps.verify.application.dto.EmailRequest;
import com.colombia.eps.verify.application.dto.RequestResponse;
import com.colombia.eps.verify.application.mapper.IVerifyMapper;
import com.colombia.eps.verify.common.util.Constants;
import com.colombia.eps.verify.domain.api.IPatientComServicePort;
import com.colombia.eps.verify.domain.api.ISqsServicePort;
import com.colombia.eps.verify.domain.api.IVerifyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyHandler implements IVerifyHandler {
    private final ISqsServicePort sqsServicePort;
    private final IPatientComServicePort patientComServicePort;
    private final IVerifyServicePort verifyServicePort;
    private final IVerifyMapper verifyMapper;
    private final Map<String, String> cellPhone;
    private final String queueUrl;

    public VerifyHandler(ISqsServicePort sqsServicePort, IPatientComServicePort patientComServicePort, IVerifyServicePort verifyServicePort, IVerifyMapper verifyMapper) {
        this.sqsServicePort = sqsServicePort;
        this.patientComServicePort = patientComServicePort;
        this.verifyServicePort = verifyServicePort;
        this.verifyMapper = verifyMapper;
        this.cellPhone = new HashMap<>();
        this.queueUrl = System.getenv("QUEUE_CODIGO_SEGURIDAD_URL");
    }

    /**
     * @param emailRequest email´s patient to generate verification code
     * @return RequestResponse with status of operation
     */
    @Override
    public RequestResponse<String> sendCode(EmailRequest emailRequest) {

        String patientNumber;
        if (cellPhone.containsKey(emailRequest.getEmail())) {
            patientNumber = cellPhone.get(emailRequest.getEmail());
        } else {
            patientNumber = patientComServicePort.getPatientNumber(emailRequest.getEmail());
            cellPhone.put(emailRequest.getEmail(), patientNumber);
        }
        Map<String, String> response = new HashMap<>();
        if (!patientNumber.isBlank()) {
            response = sqsServicePort.sendMessageQueue(patientNumber, queueUrl);
        } else {
            response.put(Constants.STATUS, Constants.CODE_500);
            response.put(Constants.MESSAGE, Constants.FAILED_COMMUNICATION);
        }
        return verifyMapper.toRequestResponse(response);
    }

    /**
     * @param codeRequest
     * @return
     */
    @Override
    public RequestResponse<String> validateCode(CodeRequest codeRequest) {
        String patientNumber = cellPhone.get(codeRequest.getEmail());
        Map<String, String> response = verifyServicePort.validateCode(verifyMapper.toCode(codeRequest, patientNumber));
        return verifyMapper.toRequestResponse(response);
    }

    /**
     *scheduled method that is executed to clean the map containing the patients' emails and phone numbers
     */
    @Override
    @Scheduled(cron = "0 30 0 ? * *")
    public void clearCellPhone() {
        this.cellPhone.clear();
    }
}