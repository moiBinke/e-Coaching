package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.exceptions.ClasseException.ClasseHasNoCycleException;
import com.novem.cours.exceptions.ClasseException.ClasseNameExistException;

import static com.novem.cours.exceptions.ClasseException.*;

public class ClasseValidator {

	@Autowired 
	private static ClasseDao classeDao;
	
	public static void validate(String nomClasse, String nomCycle) throws ClasseHasNoCycleException,
	ClasseNameExistException{
		
		validateClasseCycle(nomCycle);
		validateClasseName(nomClasse,nomCycle);
	}
	
	public static void validateClasseCycle(String nomCycle) throws ClasseHasNoCycleException {
		if(!classeDao.existsByCycleNom(nomCycle)) {
			throw new ClasseHasNoCycleException("Ce cycle n'existe pas");
		}
	}
	public static void validateClasseName(String nomClasse,String nomCycle) throws ClasseNameExistException {
		if(classeDao.existsByNomAndCycleNom(nomClasse, nomCycle)) {
			throw new ClasseNameExistException("une classe de même nom existe pour ce cycle");
		}
	}
	
	
}
