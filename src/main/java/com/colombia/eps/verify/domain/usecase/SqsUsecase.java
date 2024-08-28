package com.colombia.eps.verify.domain.usecase;

import com.colombia.eps.verify.domain.api.ISqsServicePort;
import com.colombia.eps.verify.domain.spi.ISqsPersistencePort;

import java.util.Map;

public class SqsUsecase implements ISqsServicePort {

    private final ISqsPersistencePort sqsPersistencePort;

    public SqsUsecase(ISqsPersistencePort sqsPersistancePort) {
        this.sqsPersistencePort = sqsPersistancePort;
    }

    /**
     * @param telephone patient phone number to validate
     * @param queueUrl url of queue codigo-seguridad
     * @return map with status operation
     */
    @Override
    public Map<String, String> sendMessageQueue(String telephone, String queueUrl) {
        return sqsPersistencePort.sendMessage(telephone, queueUrl);
    }
}