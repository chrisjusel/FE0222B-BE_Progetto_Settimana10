package com.secretary.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.secretary.models.Student;

public class DummyDb {
	private Map<String, Student> students;

	public DummyDb() {
		students = new HashMap<>();
	}
	
	public Student insertStudent(Student s) {
		if(!students.containsKey(s.getStudentId())) {
			students.put(s.getStudentId(), s);
			return s;
		}
		return null;
	}
	
	public boolean deleteStudent(String studentId) {
		if(students.containsKey(studentId)) {
			students.remove(studentId);
			return true;
		}
		return false;
	}
	
	public boolean updateStudent(Student s) {
		if(students.containsKey(s.getStudentId())) {
			students.put(s.getStudentId(), s);
			return true;
		}
		return false;
	}
	
	public List<Student> getAllStudents(){
		return new ArrayList<>(students.values());
	}
	
	
	
	
}
