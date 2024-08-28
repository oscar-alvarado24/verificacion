package com.colombia.eps.verify.infrastructure.output.feing.client;

import com.medicine.library.domain.GraphqlRequestBody;
import com.medicine.library.domain.ResponseFeignPragmatic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PatientClient", url = "${patient.service.url}")
public interface PatientClient {
    @PostMapping()
    ResponseFeignPragmatic getPatient(@RequestBody GraphqlRequestBody graphQLRequestBody);
}
