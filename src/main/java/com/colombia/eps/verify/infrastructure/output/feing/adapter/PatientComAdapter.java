package com.colombia.eps.verify.infrastructure.output.feing.adapter;

import com.colombia.eps.verify.common.util.Constants;
import com.colombia.eps.verify.domain.spi.IPatientComPersistencePort;
import com.colombia.eps.verify.infrastructure.output.feing.client.PatientClient;
import com.medicine.library.domain.GraphqlRequestBody;
import com.medicine.library.domain.ResponseFeignPragmatic;
import com.medicine.library.exceptions.NotComunicationWhitPatientException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.medicine.library.GenerateSchema.generateSchema;

@RequiredArgsConstructor
public class PatientComAdapter implements IPatientComPersistencePort {
    private final PatientClient patientClient  ;
    /**
     * @param email patient's email to search
     * @return patient's number
     */
    @Override
    public String getPatientNumber(String email) {
        try {
            Map<String,String> variables = new HashMap<>();
            variables.put(Constants.PATIENT_EMAIL,email);
            GraphqlRequestBody schemaComunicationAC = generateSchema(Constants.NAME_FILE_SCHEMA, variables);
            ResponseFeignPragmatic patientResponse = patientClient.getPatient(schemaComunicationAC);
            return patientResponse.getData().getGetPatient().getCellPhone();
        }catch (RetryableException e){
            throw new NotComunicationWhitPatientException();
        }
    }
}