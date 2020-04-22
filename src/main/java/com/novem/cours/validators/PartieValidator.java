package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.PartieDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.exceptions.PartieException.PartieNameExistException;

import static com.novem.cours.exceptions.PartieException.*;

public class PartieValidator {

	@Autowired private static PartieDao partieDao;
	
	public static void validate(Lecon lecon,String nom) throws PartieNameExistException {
		validateName(lecon,nom);
	}
	
	public static void validateName(Lecon lecon,String nom) throws PartieNameExistException{
		if(partieDao.existsByNomAndLecon(nom,lecon)) {
			throw new PartieNameExistException("une partie de cette leçcon porte déjà le même nom");
		}
	}
}
