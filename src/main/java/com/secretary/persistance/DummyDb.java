package com.secretary.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.secretary.models.DegreeCourse;
import com.secretary.models.Student;

public class DummyDb {
	private Map<String, Student> students;
	private Map<String, DegreeCourse> degreeCoruse;

	public DummyDb() {
		students = new HashMap<>();
		degreeCoruse = new HashMap<>();
	}

	public Student insertStudent(Student s) {
		if (!students.containsKey(s.getStudentId())) {
			students.put(s.getStudentId(), s);
			return s;
		}
		return null;
	}

	public boolean deleteStudent(String studentId) {
		if (students.containsKey(studentId)) {
			students.remove(studentId);
			return true;
		}
		return false;
	}

	public boolean updateStudent(Student s) {
		if (students.containsKey(s.getStudentId())) {
			students.put(s.getStudentId(), s);
			return true;
		}
		return false;
	}

	public List<Student> getAllStudents() {
		return new ArrayList<Student>(students.values());
	}

	public Student getStudentById(String id) {
		if(students.containsKey(id))
			return students.get(id);
		return null;
	}
	
	public DegreeCourse insertDegreeCourse(DegreeCourse d) {
		if (!degreeCoruse.containsKey(d.getCourseId())) {
			degreeCoruse.put(d.getCourseId(), d);
			return d;
		}
		return null;
	}

	public boolean deleteDegreeCourse(String courseId) {
		if (degreeCoruse.containsKey(courseId)) {
			degreeCoruse.remove(courseId);
			return true;
		}
		return false;
	}

	public boolean updateDegreeCourse(DegreeCourse d) {
		if (degreeCoruse.containsKey(d.getCourseId())) {
			degreeCoruse.put(d.getCourseId(), d);
			return true;
		}
		return false;
	}

	public List<DegreeCourse> getAllDegreeCourses() {
		return new ArrayList<DegreeCourse>(degreeCoruse.values());
	}
	
	public DegreeCourse getDegreeCourseById(String id) {
		return degreeCoruse.get(id);
	}

}
