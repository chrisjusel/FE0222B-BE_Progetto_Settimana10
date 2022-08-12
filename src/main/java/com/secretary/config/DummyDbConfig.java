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
		
		DegreeCourse dc1 = new DegreeCourse("L31", "Informatica", "Ingegneria", 21);
		DegreeCourse dc2 = new DegreeCourse("L27", "Scienze e Tecnologie Chimiche", "Ingegneria", 21);
		DegreeCourse dc3 = new DegreeCourse("L33", "Scienze Economiche", "Economia", 20);
		DegreeCourse dc4 = new DegreeCourse("L42", "Scienze e Tecniche dell'Edilizia", "Ingegneria", 20);
		DegreeCourse dc5 = new DegreeCourse("L05", "Filosofia", "", 18);
		
		dummyDb.insertDegreeCourse(dc1);
		dummyDb.insertDegreeCourse(dc2);
		dummyDb.insertDegreeCourse(dc3);
		dummyDb.insertDegreeCourse(dc4);
		dummyDb.insertDegreeCourse(dc5);
		
		Student s1 = new Student("N86002804", "Christian", "Vitale", "2000-06-03", "christian00.vitale@gmail.com",
				"Piazza Degli Artisti 7/c", "Napoli", dc1);
		
		Student s2 = new Student("N78004256", "Mario", "Rossi", "1996-08-17", "mario.rossi@gmail.com",
				"Via dei Rossi 11", "Roma", dc2);
		
		Student s3 = new Student("N42009657", "Serena", "Verdi", "1998-11-11", "serena.verdi@gmail.com",
				"Via dei Verdi 13", "Milano", dc3);
		
		Student s4 = new Student("N42001568", "Giada", "Neri", "1999-07-25", "giada.neri@gmail.com",
				"Via dei neri 67", "Torino", dc4);
		
		dummyDb.insertStudent(s1);
		dummyDb.insertStudent(s2);
		dummyDb.insertStudent(s3);
		dummyDb.insertStudent(s4);

		return dummyDb;
	}
}
