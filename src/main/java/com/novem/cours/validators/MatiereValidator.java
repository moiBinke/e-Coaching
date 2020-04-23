package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.exceptions.MatiereException.MatiereNameExistException;

import static com.novem.cours.exceptions.MatiereException.*;

public class MatiereValidator {

	
	public static void validate(Classe classe, String nom,MatiereDao matiereDao) throws MatiereNameExistException {
		validateName(classe,nom,matiereDao);
	}
	public static void validateName(Classe classe, String nom,MatiereDao matiereDao) throws MatiereNameExistException {
		if(nom!=null && !nom.trim().equals("")) {
			if(matiereDao.existsByNomAndClasse(nom,classe)) {
				throw new MatiereNameExistException("une matière de meme nom existe");
			}
		}else {
			throw new MatiereNameExistException("une matière ne peut pas avoir un nom vide");

		}
	}
}
