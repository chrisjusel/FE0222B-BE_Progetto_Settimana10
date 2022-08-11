package com.secretary.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secretary.config.DummyDbConfig;
import com.secretary.models.Student;
import com.secretary.persistance.DummyDb;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@PostMapping("/add")
	public String addStudent(@RequestBody Student student, Model model) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(DummyDbConfig.class);
		DummyDb dummyDb = (DummyDb) ctx.getBean("dummyDbInit");
		dummyDb.insertStudent(student);
		dummyDb.getAllStudents().forEach(s -> System.out.println(s.getName()));
		return "add";
	}
}
