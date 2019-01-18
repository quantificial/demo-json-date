package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
		
		log.info("--- application start ---");
		
		
		Date newDate = new Date();		
		log.info("normal java date format: " + newDate.toString());
		
		EncapClass a = new EncapClass();
		
		a.setId("10");
		a.setMessage("hello");
		
		log.info(a.toString());
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("target/car.json"), a);
		
		log.info("--- value a... from Java Object Instance...");
		log.info("In object format: " + a.toString());
		
		// In JSON format: {"currentDate":"2018-12-27T02:04:32.962+0000","id":"10","message":"hello"}
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		Date date = isoFormat.parse("2019-01-11T14:30:00");
		
		a.setCurrentDate(date);
		
		log.info("In JSON format: " + objectMapper.writeValueAsString(a));

		
		BufferedReader br = new BufferedReader(new FileReader("target/car.json"));
		
		log.info("--- value b... object load from JSON file...");
		EncapClass b = objectMapper.readValue(br, EncapClass.class);
		log.info("In object format: " + b.toString());
		
		//In JSON format: {"currentDate":"2018-12-27T02:05:17.844+0000","id":"10","message":"hello"}
		log.info("In JSON format: " + objectMapper.writeValueAsString(b));
		
		
		log.info("Object A is equal to Object B: " + a.equals(b));
						
//		{
//			  "policyNumber": "1234567890",
//			  "uploadId": "T000000003",
//			  "uploadStatusTime": "2018-12-12T03:54:40.924+0000",
//			  "uploadTime": "2018-12-12T03:54:40.924+0000"
//		}
		
	}
	

}
