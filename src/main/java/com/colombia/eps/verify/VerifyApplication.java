package com.colombia.eps.verify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients()
@ImportAutoConfiguration(FeignAutoConfiguration.class)
public class VerifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifyApplication.class, args);
	}

}
