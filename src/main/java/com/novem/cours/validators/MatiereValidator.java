package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.exceptions.MatiereException.MatiereNameExistException;

import static com.novem.cours.exceptions.MatiereException.*;

public class MatiereValidator {

	@Autowired public static MatiereDao matiereDao;
	
	public static void validate(Classe classe, String nom) throws MatiereNameExistException {
		validateName(classe,nom);
	}
	public static void validateName(Classe classe, String nom) throws MatiereNameExistException {
		if(matiereDao.existsByNomAndClasse(nom,classe)) {
			throw new MatiereNameExistException("une matière de meme nom existe");
		}
	}
}
