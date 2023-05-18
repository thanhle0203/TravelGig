package com.thanhle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.thanhle.domain")
public class AutoInsuranceMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoInsuranceMicroserviceApplication.class, args);
	}

}
