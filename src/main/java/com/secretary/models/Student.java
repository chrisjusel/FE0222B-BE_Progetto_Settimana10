package com.secretary.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {

	private String studentId;
	private String name;
	private String surname;
	private Date bornDate;
	private String email;
	private String indirizzo;
	private String city;
	private DegreeCourse degreeCourse;

}
