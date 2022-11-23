package com.example.ticketbookingapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class TicketBookingAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketBookingAppApplication.class, args);
	}

	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
