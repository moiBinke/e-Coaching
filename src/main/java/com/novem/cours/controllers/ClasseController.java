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

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.CycleDao;
import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Cycle;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.ClasseException;
import com.novem.cours.exceptions.ClasseException.ClasseHasNoCycleException;
import com.novem.cours.exceptions.ClasseException.ClasseNameExistException;
import com.novem.cours.service.ClasseService;
import com.novem.cours.validators.ClasseValidator;

@RestController
@RequestMapping("/classes")
@CrossOrigin("*")
public class ClasseController {

	@Autowired
	private ClasseService classeService;
	@Autowired 
	private ClasseDao classeDao;
	@Autowired
	private  CycleDao cycleDao;
	@Autowired MatiereDao matiereDao;
	
	@GetMapping("")
	public Collection<Classe> findAll() {
		return classeDao.findAll();
	}
	@PostMapping("/creer/{idCycle}")
	public ResponseEntity<Object> creerClasse(@PathVariable("idCycle")Long idCycle,@RequestBody Classe classe) {
		Map<String,String> errors=new HashMap();
		
		try {
			if(!cycleDao.existsById(idCycle)) {
				throw new ClasseException.ClasseHasNoCycleException("IL n'y a pas de cycle ayant cet id");
			}
			Cycle cycle = cycleDao.getOne(idCycle);
			ClasseValidator.validate(classe.getNom(), cycle.getNom(),classeDao,cycleDao);
			return new ResponseEntity<Object>(classeService.creerClasse(classe,cycle),HttpStatus.OK);

		} catch (ClasseHasNoCycleException e) {
			errors.put("cycleNotExistsErrors", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (ClasseNameExistException e) {
			errors.put("nameError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{idClasse}")
	public Optional<Classe> findById(@PathVariable("idClasse")Long idClasse) {
		return classeDao.findById(idClasse);
	}
	
}
