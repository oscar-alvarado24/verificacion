package com.colombia.eps.verify.domain.usecase;

import com.colombia.eps.verify.domain.api.IPatientComServicePort;
import com.colombia.eps.verify.domain.spi.IPatientComPersistencePort;

public class PatientComUsecase implements IPatientComServicePort {

    private final IPatientComPersistencePort patientComPersistencePort;

    public PatientComUsecase(IPatientComPersistencePort patientComPersistencePort) {
        this.patientComPersistencePort = patientComPersistencePort;
    }
    /**
     * @param email of the patient whose number is to be obtained
     * @return cellphone of patient
     */
    @Override
    public String getPatientNumber(String email) {
        return patientComPersistencePort.getPatientNumber(email);
    }
}
