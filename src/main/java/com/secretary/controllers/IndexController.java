/**
 * Questa classe ha il solo scopo di reindirizzare l'utente alla pagina
 * index quando va sull'url dell'applicazione
 */
package com.secretary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
}
