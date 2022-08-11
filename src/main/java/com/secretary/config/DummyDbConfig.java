package com.secretary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.secretary.persistance.DummyDb;

@Configuration
public class DummyDbConfig {
	
	@Bean
	@Scope("singleton")
	public DummyDb dummyDbInit() {
		
		DummyDb dummyDb = new DummyDb();
		
		//dummyDb.insertStudent(new Student("N100", "Christian", "Vitale", new Date("2000-06-03"), "christianvit@hotmail.it", "indirizzo", "citta", new DegreeCourse(null, null, null, null)));
		
		return dummyDb;
	}
}
