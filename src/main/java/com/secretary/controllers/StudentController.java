package com.secretary.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.secretary.dto.StudentDto;
import com.secretary.models.DegreeCourse;
import com.secretary.models.Student;
import com.secretary.persistance.DummyDb;
import com.secretary.util.BeanBuilder;
import com.secretary.util.StudentDtoConverter;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	/**
	 * Recupero dell'istanza del db
	 */
	DummyDb dummyDb = BeanBuilder.getIstance();

	/**
	 * Questo metodo recupera tutti gli studenti dalla base dati, li aggiunge
	 * alla ModelMap per passarli al frontend e reindirizza alla pagina di
	 * visualizzazione degli studenti
	 * @param m
	 * @return
	 */
	@GetMapping("/showStudents")
	public String getAll(ModelMap m) {
		List<Student> students = dummyDb.getAllStudents();
		m.addAttribute("students", students);
		return "showStudents";
	}

	/**
	 * Questo metodo rende possibile la visualizzazione della pagina che mostra
	 * il form che permette di inserire un nuovo studente
	 * @param m
	 * @return
	 */
	@GetMapping(value = "/insertStudent")
	public ModelAndView showFormInsert(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();;
		m.addAttribute("courses", courses);
		return new ModelAndView("insertStudent", "student", new StudentDto());
	}

	/**
	 * Questo metodo viene scatenato al submit dei dati di un nuovo studente;
	 * Se tutti i campi del form di inserimento sono stati soddisfatti,
	 * un nuovo studente viene inserito, altrimenti vengono visualizzati
	 * i messaggi di errore dei form che indicano ciò che va modificato
	 * @param course
	 * @param result
	 * @param m
	 * @return
	 */
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

	/**
	 * Questo metodo rende possibile la visualizzazione della pagina che mostra
	 * il form che permette di modificare uno studente
	 * @param m
	 * @return
	 */
	@GetMapping(value = "/updateStudent/{studentId}")
	public ModelAndView showFormUpdate(ModelMap m, @PathVariable(name = "studentId") String studentId) {
		Student student = dummyDb.getStudentById(studentId);
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return new ModelAndView("updateStudent", "student", student);
	}

	/**
	 * Questo metodo viene scatenato al submit dei dati di uno studente da modificare;
	 * Se tutti i campi del form di inserimento sono stati soddisfatti,
	 * lo studente viene modificato, altrimenti vengono visualizzati
	 * i messaggi di errore dei form che indicano ciò che va modificato
	 * @param course
	 * @param result
	 * @param m
	 * @return
	 */
	@PostMapping(value = "/updateStudentFromForm")
	public String update(StudentDto studentDto) {
		Student student = StudentDtoConverter.convertToStudent(studentDto);
		System.out.println(dummyDb.updateStudent(student));
		System.out.println(student.getStudentId() + " " + student.getName());
		return "redirect:showStudents";
	}

	/**
	 * Questo metodo viene scatenato quando un utente clicca sul tasto delete
	 * accanto ad uno studente;
	 * All'url viene passato l'id dello studente da eliminare, ed esso viene eliminato
	 * @param m
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/deleteStudent/{id}")
	public String delete(ModelMap m, @PathVariable("id") String id) {
		dummyDb.deleteStudent(id);
		return "redirect:/students/showStudents";
	}

}
