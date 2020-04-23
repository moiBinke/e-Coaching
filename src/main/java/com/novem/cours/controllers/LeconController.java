package com.novem.cours.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.novem.cours.dao.LeconDao;
import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.LeconException.LeconMatiereNotFound;
import com.novem.cours.exceptions.LeconException.LeconNameExistException;

import static com.novem.cours.exceptions.LeconException.*;

import com.novem.cours.service.LeconService;
import com.novem.cours.service.MatiereService;
import com.novem.cours.validators.LeconValidator;

@RestController
@RequestMapping("/lecons")
@CrossOrigin("*")
public class LeconController {
	
	@Autowired private LeconDao leconDao;
	@Autowired private MatiereDao matiereDao;
	@Autowired private LeconService leconService;
	
	
	@PostMapping("/creer/{idMatiere}")
	public ResponseEntity<Object> creerLecon(@PathVariable("idMatiere") Long idMatiere, @RequestBody Lecon lecon) {
		
		Map<String, String> errors =new HashMap<>();
		
		Optional<Matiere> matiere=matiereDao.findById(idMatiere);
		try {
			if(!matiereDao.existsById(idMatiere)) {
				 throw new LeconMatiereNotFound("On peut pas affecter une Leçon à une matière inexistante! ");
			}
			LeconValidator.validate(matiere.get(), lecon.getNom(),leconDao);
		    return new ResponseEntity<Object>(leconService.creerLecon(matiere,lecon),HttpStatus.OK);
		} catch (LeconNameExistException e) {
			errors.put("nameError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (LeconMatiereNotFound e) {
			errors.put("matiereNotExistsErrors", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/matieres/{idMatiere}")
	public Collection<Lecon> findMyMatieres(@PathVariable("idMatiere")Long idMatiere){
		return leconDao.findByMatiereId(idMatiere);
	} 
}
