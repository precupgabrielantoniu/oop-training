package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages = "com.example.accessingdatamysql")
@EnableJpaRepositories("com.example.accessingdatamysql.repository")
@ComponentScan(basePackages= {"com.example.accessingdatamysql.controller", "com.example.accessingdatamysql.service"})
@EntityScan("com.example.accessingdatamysql.entity")
@CrossOrigin(origins = "*",  allowedHeaders = "*")
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

}
