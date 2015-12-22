package com.fureading.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fureading.persistence.model.ELivre;
import com.fureading.persistence.model.Status;
import com.fureading.services.LivreService;




@Controller  
@RequestMapping("/livre")
public class LivreController {
	
	@Autowired
	LivreService livreService;
	
	static final Logger logger = Logger.getLogger(LivreController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addLivre(@RequestBody ELivre livre) {
		try {
			livreService.addLivre(livre);
			return new Status(1, "ELivre added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ELivre getELivre(@PathVariable("id") long id) {
		ELivre livre = null;
		try {
			livre = livreService.getLivreById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return livre;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<ELivre> getELivre() {

		List<ELivre> ELivreList = null;
		try {
			ELivreList = livreService.getLivreList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ELivreList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteELivre(@PathVariable("id") long id) {

		try {
			//livreService.deleteLivre(livre);
			return new Status(1, "ELivre deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}

}
