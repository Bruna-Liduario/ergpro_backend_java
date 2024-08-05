package com.ergproapontamento.ergpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ergproapontamento.ergpro")
public class ErgproApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErgproApplication.class, args);
	}
}
