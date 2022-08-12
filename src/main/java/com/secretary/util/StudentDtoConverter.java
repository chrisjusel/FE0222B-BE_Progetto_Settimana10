package com.secretary.util;

import com.secretary.dto.StudentDto;
import com.secretary.models.Student;

public abstract class StudentDtoConverter {
	
	public static Student convertToStudent(StudentDto studentDto) {
		DegreeCourseConverter converter = new DegreeCourseConverter();
		Student student = new Student();
		student.setStudentId(studentDto.getStudentId());
		student.setName(studentDto.getName());
		student.setSurname(studentDto.getSurname());
		student.setBornDate(studentDto.getBornDate());
		student.setAddress(studentDto.getAddress());
		student.setCity(studentDto.getCity());
		student.setEmail(studentDto.getEmail());
		student.setDegreeCourse(converter.convert(studentDto.getDegreeCourse()));
		System.out.println("da converter " + student.getDegreeCourse().getName());
		return student;
	}
}
