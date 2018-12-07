package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Something else is wrong here");
		
		
		Date newDate = new Date();		
		log.info(newDate.toString());
		
		EncapClass a = new EncapClass();
		
		a.setId("10");
		a.setMessage("hello");
		
		log.info(a.toString());
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("target/car.json"), a);
		
		log.info("value a..........................");
		log.info(a.toString());
		log.info(objectMapper.writeValueAsString(a));
		
		
		BufferedReader br = new BufferedReader(new FileReader("target/car.json"));
		
		log.info("value b..........................");
		EncapClass b = objectMapper.readValue(br, EncapClass.class);
		log.info(b.toString());
		log.info(objectMapper.writeValueAsString(b));
		
		
	}
	

}
