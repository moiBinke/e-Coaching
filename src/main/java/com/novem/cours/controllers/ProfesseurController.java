package com.novem.cours.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Professeur;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassDejaAffecte;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassNotFoun;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailExist;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailNotCorrect;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurWrongPassword;
import com.novem.cours.service.ProfesseurService;
import com.novem.cours.validators.ProfesseurValidator;

@RestController
@RequestMapping("/professeurs")
@CrossOrigin("*")
public class ProfesseurController {

	@Autowired private ProfesseurService professeurService;
	@Autowired private ProfesseurDao professeurDao;
	@Autowired private ClasseDao classeDao;
	
	
	
	@PostMapping("/creer/{lesIdClasses}")
	public ResponseEntity<Object>  creerProfesseur(@PathVariable("lesIdClasses")String lesIdClasses, 
			@RequestBody Professeur professeur) {
		Map<String, String>errors=new HashMap<String, String>();
		String [] listeIdClasse = lesIdClasses.split("_");
		System.out.println(listeIdClasse);
		try {
			ProfesseurValidator.validate(professeur, classeDao, listeIdClasse,professeurDao);
			return new ResponseEntity<Object>(professeurService.creerProfesseur(professeur,listeIdClasse),HttpStatus.OK);
		} catch (ProfesseurEmailNotCorrect e) {
			errors.put("emailNotCorrectError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);			
		} catch (ProfesseurEmailExist e) {
			errors.put("emailExistError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurWrongPassword e) {
			errors.put("wrongPasswordError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurClassNotFoun e) {
			errors.put("classeNotExisteError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurClassDejaAffecte e) {
			errors.put("classeAlreadyAffecetedError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("/{email}/{password}")
	public Professeur getProfesseur(@PathVariable("email")String email,@PathVariable("password") String password) {
		return professeurService.findByEmailAndPassword(email,password);
	}
	
	
}
