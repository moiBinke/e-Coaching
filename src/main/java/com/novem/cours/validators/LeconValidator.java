package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.LeconDao;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.LeconException.LeconNameExistException;

import static com.novem.cours.exceptions.LeconException.*;
;public class LeconValidator {
	
	@Autowired public static LeconDao leconDao;
	
	public static void validate(Matiere matiere,String nom) throws LeconNameExistException {
		 validateName(matiere, nom);
	}
	
	public static void validateName(Matiere matiere,String nom) throws LeconNameExistException{
		if(leconDao.existsByNomAndMatiere(nom,matiere)) {
			throw new LeconNameExistException("Une lecon de meme nom existe dejà");
		}
	}
	

}
