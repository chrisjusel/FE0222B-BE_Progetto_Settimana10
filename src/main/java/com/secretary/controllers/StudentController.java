package com.secretary.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.secretary.config.DummyDbConfig;
import com.secretary.dto.StudentDto;
import com.secretary.models.DegreeCourse;
import com.secretary.models.Student;
import com.secretary.persistance.DummyDb;
import com.secretary.util.BeanBuilder;
import com.secretary.util.StudentDtoConverter;

@Controller
@RequestMapping("/students")
public class StudentController {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(DummyDbConfig.class);
	DummyDb dummyDb = BeanBuilder.getIstance();

	@GetMapping("/showStudents")
	public String getAll(ModelMap m) {
		List<Student> students = dummyDb.getAllStudents();
		m.addAttribute("students", students);
		return "showStudents";
	}

	@GetMapping(value = "/insertStudent")
	public ModelAndView showFormInsert(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();;
		m.addAttribute("courses", courses);
		return new ModelAndView("insertStudent", "student", new StudentDto());
	}

	@PostMapping(value = "/insertStudentFromForm")
	public String submitAdd(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result, ModelMap m) {
		if (!result.hasErrors()) {
			System.out.println(studentDto.getDegreeCourse());
			Student student = StudentDtoConverter.convertToStudent(studentDto);
			dummyDb.insertStudent(student);
		} else {
			List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
			m.addAttribute("courses", courses);
			m.addAttribute("student", studentDto);
			return "insertStudent";
		}
		return "redirect:showStudents";
	}

	@GetMapping(value = "/updateStudent/{studentId}")
	public ModelAndView showFormUpdate(ModelMap m, @PathVariable(name = "studentId") String studentId) {
		Student student = dummyDb.getStudentById(studentId);
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return new ModelAndView("updateStudent", "student", student);
	}

	@PostMapping(value = "/updateStudentFromForm")
	public String update(StudentDto studentDto) {
		Student student = StudentDtoConverter.convertToStudent(studentDto);
		System.out.println(dummyDb.updateStudent(student));
		System.out.println(student.getStudentId() + " " + student.getName());
		return "redirect:showStudents";
	}

	@GetMapping(value = "/deleteStudent/{id}")
	public String delete(ModelMap m, @PathVariable("id") String id) {
		dummyDb.deleteStudent(id);
		return "redirect:/students/showStudents";
	}

}
