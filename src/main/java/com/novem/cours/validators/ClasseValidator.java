package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.CycleDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.exceptions.ClasseException.ClasseHasNoCycleException;
import com.novem.cours.exceptions.ClasseException.ClasseNameExistException;

import static com.novem.cours.exceptions.ClasseException.*;

public class ClasseValidator {

	
	public static void validate(String nomClasse, String nomCycle,ClasseDao classeDao,CycleDao cycleDao) throws ClasseHasNoCycleException,
	ClasseNameExistException{
		
		validateClasseCycle(nomCycle, cycleDao);
		validateClasseName(nomClasse,nomCycle,classeDao);
	}
	
	public static void validateClasseCycle(String nomCycle,CycleDao cycleDao) throws ClasseHasNoCycleException {
		if(!cycleDao.existsByNom(nomCycle)) {
			throw new ClasseHasNoCycleException("Ce cycle n'existe pas");
		}
	}
	public static void validateClasseName(String nomClasse,String nomCycle,ClasseDao classeDao) throws ClasseNameExistException {
		if(nomClasse!=null) {
			if(classeDao.existsByNomAndCycleNom(nomClasse, nomCycle)) {
				throw new ClasseNameExistException("une classe de même nom existe pour ce cycle");
			}
		}
		else {
			throw new ClasseNameExistException("une classe ne peut pas avoir un nom à valeur null");
		}
	}
	
	
}
