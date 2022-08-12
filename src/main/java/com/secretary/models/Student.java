package com.secretary.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private String studentId;
	private String name;
	private String surname;
	private String bornDate;
	private String email;
	private String address;
	private String city;
	private DegreeCourse degreeCourse;

}
