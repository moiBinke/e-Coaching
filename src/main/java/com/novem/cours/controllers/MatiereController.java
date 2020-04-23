package com.novem.cours.controllers;

import java.util.Collection;
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
import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.MatiereException.MatiereClassNotFoundException;
import com.novem.cours.exceptions.MatiereException.MatiereNameExistException;

import static com.novem.cours.exceptions.MatiereException.*;
import com.novem.cours.service.MatiereService;
import com.novem.cours.validators.MatiereValidator;

@RestController
@RequestMapping("/matieres")
@CrossOrigin("*")
public class MatiereController {

	@Autowired private MatiereDao matiereDao;
	@Autowired private MatiereService matiereService;
	@Autowired private ClasseDao classeDao;
	
	
	@PostMapping("/creer/{idClasse}")
	public ResponseEntity<Object> creerMatiere(@PathVariable("idClasse")Long idClasse,@RequestBody Matiere matiere){
		
			Map<String,String> errors=new HashMap();
			
			Classe classe=classeDao.getOne(idClasse);
			try {
				if(!classeDao.existsById(idClasse)) {
					throw new MatiereClassNotFoundException("On peut pas affecter une matière à une classe inexistante!");
				}
				MatiereValidator.validate(classe, matiere.getNom(),matiereDao);
				return new ResponseEntity<Object>(matiereService.creerMatiere(classe,matiere),HttpStatus.OK);
			} catch (MatiereNameExistException e) {
				errors.put("nameError", e.getMessage());
				return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);

			} catch (MatiereClassNotFoundException e) {
				errors.put("classeNotExistsErrors", e.getMessage());
				return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);

			}
	}
	
	@GetMapping("/classes/{idClasse}")
	public Collection<Matiere> findMyMatieres(@PathVariable("idClasse")Long idClasse){
		return matiereDao.findByClasseId(idClasse);
	}	
	
}
