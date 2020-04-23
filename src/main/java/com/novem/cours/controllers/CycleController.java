package com.novem.cours.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novem.cours.dao.CycleDao;
import com.novem.cours.entities.Cycle;
import com.novem.cours.exceptions.CycleException.CycleNameExistException;
import com.novem.cours.service.CycleService;
import com.novem.cours.validators.CycleValidator;

@RestController
@RequestMapping("/cycles")
@CrossOrigin("*")
public class CycleController {

	@Autowired public CycleService serviceCycle;
	@Autowired public CycleDao cycleDao;
	
	@GetMapping("")
	public Collection<Cycle>findAll() {
		return cycleDao.findAll();
	}
	@PostMapping("/creer")
	public ResponseEntity<Object> creerCycle(@RequestBody Cycle cycle){
		Map<String,String> errors=new HashMap();
		try {
			CycleValidator.validate(cycle.getNom(),cycleDao);
			return new ResponseEntity<Object>(serviceCycle.creerCycle(cycle),HttpStatus.OK);
		} catch (CycleNameExistException e) {
			errors.put("nameError", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
}
