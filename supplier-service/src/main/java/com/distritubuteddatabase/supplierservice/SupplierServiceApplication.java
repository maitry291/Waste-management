package com.distritubuteddatabase.supplierservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.distritubuteddatabase.supplierservice.config.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class SupplierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierServiceApplication.class, args);
	}

}
