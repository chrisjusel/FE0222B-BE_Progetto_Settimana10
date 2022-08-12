package com.secretary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.secretary.models.DegreeCourse;
import com.secretary.models.Student;
import com.secretary.persistance.DummyDb;

@Configuration
public class DummyDbConfig {
	
	@Bean
	@Scope("singleton")
	public DummyDb dummyDbInit() {
		
		DummyDb dummyDb = new DummyDb();
		DegreeCourse dc = new DegreeCourse("N27", "Informatica", "Ingegneria elettrica", 21);
		DegreeCourse dc1 = new DegreeCourse("N41", "Filosofia", "Inutilità", 18);
		Student s = new Student("N86002380","Christian","Vitale", "2000-06-03","christianvit@hotmail.it","Piazza Degli Artisti 7/c","Napoli", dc);
		dummyDb.insertStudent(s);
		dummyDb.insertDegreeCourse(dc);
		dummyDb.insertDegreeCourse(dc1);
		return dummyDb;
	}
}
