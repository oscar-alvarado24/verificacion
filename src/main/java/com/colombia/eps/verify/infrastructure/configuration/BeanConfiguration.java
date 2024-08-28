package com.colombia.eps.verify.infrastructure.configuration;


import com.colombia.eps.verify.domain.api.IPatientComServicePort;
import com.colombia.eps.verify.domain.api.ISqsServicePort;
import com.colombia.eps.verify.domain.api.IVerifyServicePort;
import com.colombia.eps.verify.domain.spi.IPatientComPersistencePort;
import com.colombia.eps.verify.domain.spi.ISqsPersistencePort;
import com.colombia.eps.verify.domain.spi.IVerifyPersistencePort;
import com.colombia.eps.verify.domain.usecase.PatientComUsecase;
import com.colombia.eps.verify.domain.usecase.SqsUsecase;
import com.colombia.eps.verify.domain.usecase.VerifyUsecase;
import com.colombia.eps.verify.infrastructure.output.aws.sqs.adapter.SqsAdapter;
import com.colombia.eps.verify.infrastructure.output.feing.adapter.PatientComAdapter;
import com.colombia.eps.verify.infrastructure.output.feing.client.PatientClient;
import com.colombia.eps.verify.infrastructure.output.twilio.adapter.TwilioAdapter;
import com.colombia.eps.verify.infrastructure.output.twilio.mapper.ITwilioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    private final PatientClient patientClient;
    private final ITwilioMapper twilioMapper;
    private final String accountSid;
    private final String authToken;
    private final String serviceSid;

    public BeanConfiguration(PatientClient patientClient, ITwilioMapper twilioMapper) {
        this.patientClient = patientClient;
        this.twilioMapper = twilioMapper;
        this.accountSid = System.getenv("TWILIO_ACCOUNT_SID");
        this.authToken = System.getenv("TWILIO_AUTH_TOKEN");
        this.serviceSid = System.getenv("TWILIO_SERVICE_SID");
    }

    @Bean
    public IPatientComPersistencePort patientComPersistencePort() {
        return new PatientComAdapter(patientClient);
    }

    @Bean
    public IPatientComServicePort patientComServicePort() {
        return new PatientComUsecase(patientComPersistencePort());
    }

    @Bean
    public ISqsPersistencePort sqsPersistencePort() {
        return new SqsAdapter();
    }

    @Bean
    public ISqsServicePort sqsServicePort() {
        return new SqsUsecase(sqsPersistencePort());
    }

    @Bean
    public IVerifyPersistencePort verifyPersistencePort(){
        return new TwilioAdapter(accountSid, authToken, serviceSid);
    }
    @Bean
    public IVerifyServicePort verifyServicePort(){
        return new VerifyUsecase(verifyPersistencePort(),twilioMapper);
    }
}