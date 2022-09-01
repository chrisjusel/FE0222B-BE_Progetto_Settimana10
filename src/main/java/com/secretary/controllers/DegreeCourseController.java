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

import com.secretary.models.DegreeCourse;
import com.secretary.persistance.DummyDb;
import com.secretary.util.BeanBuilder;

@Controller
@RequestMapping("/courses")
public class DegreeCourseController {

	/**
	 * Recupero dell'istanza del db
	 */
	DummyDb dummyDb = BeanBuilder.getIstance();

	/**
	 * Questo metodo recupera tutti i corsi dalla base dati, li aggiunge
	 * alla ModelMap per passarli al frontend e reindirizza alla pagina di
	 * visualizzazione dei corsi
	 * @param m
	 * @return
	 */
	@GetMapping("/showCourses")
	public String getAll(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return "showCourses";
	}
	
	/**
	 * Questo metodo rende possibile la visualizzazione della pagina che mostra
	 * il form che permette di inserire un nuovo corso
	 * @param m
	 * @return
	 */
	@GetMapping(value = "/insertCourse")
	public ModelAndView showFormInsert(ModelMap m) {
		List<DegreeCourse> courses = dummyDb.getAllDegreeCourses();
		m.addAttribute("courses", courses);
		return new ModelAndView("insertCourse", "course", new DegreeCourse());
	}
	
	/**
	 * Questo metodo viene scatenato al submit dei dati di un nuovo corso;
	 * Se tutti i campi del form di inserimento sono stati soddisfatti,
	 * un nuovo corso viene inserito, altrimenti vengono visualizzati
	 * i messaggi di errore dei form che indicano ciò che va modificato
	 * @param course
	 * @param result
	 * @param m
	 * @return
	 */
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
	
	/**
	 * Questo metodo rende possibile la visualizzazione della pagina che mostra
	 * il form che permette di modificare un corso
	 * @param m
	 * @return
	 */
	@GetMapping(value = "/updateCourse/{courseId}")
	public ModelAndView showFormUpdate(@PathVariable(name = "courseId") String courseId) {
		DegreeCourse course = dummyDb.getDegreeCourseById(courseId);
		return new ModelAndView("updateCourse", "course", course);
	}
	
	/**
	 * Questo metodo viene scatenato al submit dei dati di un corso da modificare;
	 * Se tutti i campi del form di inserimento sono stati soddisfatti,
	 * il corso viene modificato, altrimenti vengono visualizzati
	 * i messaggi di errore dei form che indicano ciò che va modificato
	 * @param course
	 * @param result
	 * @param m
	 * @return
	 */
	@PostMapping(value = "/updateCourseFromForm")
	public String update(@Valid @ModelAttribute("course") DegreeCourse course, BindingResult result, ModelMap m) {
		dummyDb.updateDegreeCourse(course);
		return "redirect:showCourses";
	}
	
	/**
	 * Questo metodo viene scatenato quando un utente clicca sul tasto delete
	 * accanto ad un corso;
	 * All'url viene passato l'id dell'utente da eliminare, ed esso viene eliminato
	 * @param m
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/deleteCourse/{id}")
	public String delete(ModelMap m, @PathVariable("id") String id) {
		dummyDb.deleteDegreeCourse(id);
		return "redirect:/courses/showCourses";
	}
}
