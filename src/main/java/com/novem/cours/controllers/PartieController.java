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
import com.novem.cours.dao.PartieDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Partie;
import com.novem.cours.exceptions.PartieException.PartieContenuException;
import com.novem.cours.exceptions.PartieException.PartieLeconNotFound;
import com.novem.cours.exceptions.PartieException.PartieNameExistException;

import static com.novem.cours.exceptions.PartieException.*;
import com.novem.cours.service.PartieService;
import com.novem.cours.validators.LeconValidator;
import com.novem.cours.validators.PartieValidator;
@RestController
@RequestMapping("/parties")
@CrossOrigin("*")
public class PartieController {

	@Autowired private PartieService partieService;
	@Autowired private PartieDao partieDao;
	@Autowired private LeconDao leconDao;
	
	@PostMapping("/creer/{idLecon}")
	public ResponseEntity<Object> creerPartie(@PathVariable("idLecon")Long idLecon,@RequestBody Partie partie) {
		Map<String,String> errors = new HashMap<>();
		
		try {
			if(!leconDao.existsById(idLecon)) {
				throw new PartieLeconNotFound("IL n'y a pas de Leçon ayant cet id");
			}
			Optional<Lecon> lecon= leconDao.findById(idLecon);
			PartieValidator.validate(lecon.get(), partie, partieDao);
			return new ResponseEntity<Object>(partieService.creerPartie(lecon,partie),HttpStatus.OK);
		} catch (PartieNameExistException e) {
			errors.put("nameError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (PartieLeconNotFound e) {
			errors.put("leconNotExistError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (PartieContenuException e) {
			errors.put("contentError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/lecons/{idLecon}")
	public Collection<Partie> getPartiesOfLecon(@PathVariable("idLecon")Long idLecon ){
		return partieDao.findByLeconId(idLecon);
	}
	
}
