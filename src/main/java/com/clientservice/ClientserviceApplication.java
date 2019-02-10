package com.clientservice;

import javax.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ClientserviceApplication {

    @Bean
    public Validator validatorFactory() {
        return new LocalValidatorFactoryBean();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ClientserviceApplication.class, args);
    }

}

