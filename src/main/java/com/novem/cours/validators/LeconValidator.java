package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.LeconDao;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.LeconException.LeconNameExistException;

import static com.novem.cours.exceptions.LeconException.*;
;public class LeconValidator {
	
	
	public static void validate(Matiere matiere,String nom,LeconDao leconDao) throws LeconNameExistException {
		 validateName(matiere, nom,leconDao);
	}
	
	public static void validateName(Matiere matiere,String nom,LeconDao leconDao) throws LeconNameExistException{
		if(nom!=null && !nom.trim().equals("")) {
			if(leconDao.existsByNomAndMatiere(nom,matiere)) {
				throw new LeconNameExistException("Une lecon de meme nom existe dejà");

			}
		}else {
			throw new LeconNameExistException("Une lecon ne peut pas avoir un nom null");	
		}
	}	

}
