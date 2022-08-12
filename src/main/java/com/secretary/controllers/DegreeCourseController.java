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
import com.secretary.models.DegreeCourse;
import com.secretary.persistance.DummyDb;
import com.secretary.util.BeanBuilder;

@Controller
@RequestMapping("/courses")
public class DegreeCourseController {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(DummyDbConfig.class);
	DummyDb dummyDb = BeanBuilder.getIstance();

	@GetMapping("/showCourses")
	public String getAll(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return "showCourses";
	}
	
	@GetMapping(value = "/insertCourse")
	public ModelAndView showFormInsert(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return new ModelAndView("insertCourse", "course", new DegreeCourse());
	}
	
	@PostMapping(value = "/insertCourseFromForm")
	public String submitAdd(@Valid @ModelAttribute("course") DegreeCourse course, BindingResult result, ModelMap m) {
		if (!result.hasErrors()) {
			dummyDb.insertDegreeCourse(course);
		} else {
			List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
			m.addAttribute("courses", courses);
			return "insertCourse";
		}
		return "redirect:showCourses";
	}
	
	@GetMapping(value = "/updateCourse/{courseId}")
	public ModelAndView showFormUpdate(@PathVariable(name = "courseId") String courseId) {
		DegreeCourse course = dummyDb.getDegreeCourseById(courseId);
		return new ModelAndView("updateCourse", "course", course);
	}
	
	@PostMapping(value = "/updateCourseFromForm")
	public String update(@Valid @ModelAttribute("course") DegreeCourse course, BindingResult result, ModelMap m) {
		dummyDb.updateDegreeCourse(course);
		return "redirect:showStudents";
	}
	
	@GetMapping(value = "/deleteCourse/{id}")
	public String delete(ModelMap m, @PathVariable("id") String id) {
		dummyDb.deleteDegreeCourse(id);
		return "redirect:/courses/showCourses";
	}
}
