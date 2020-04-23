package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.PartieDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Partie;
import com.novem.cours.exceptions.PartieException.PartieContenuException;
import com.novem.cours.exceptions.PartieException.PartieNameExistException;

import static com.novem.cours.exceptions.PartieException.*;

public class PartieValidator {

	
	public static void validate(Lecon lecon,Partie partie,PartieDao partieDao) throws PartieNameExistException, PartieContenuException {
		validateName(lecon,partie.getNom(),partieDao);
		validateContenu(partie);
	}
	
	public static void validateName(Lecon lecon,String nom,PartieDao partieDao) throws PartieNameExistException{
		if(isValide(nom)) {
			if(partieDao.existsByNomAndLecon(nom,lecon)) {
				throw new PartieNameExistException("une partie de cette leçcon porte déjà le même nom");
			}
		}else {
			throw new PartieNameExistException("le nom d'une partie de leçcon ne peut pas être vide");
		}
	}
	private static boolean isValide(String x) {
		if(x== null || x=="") {
			return false;
		}
		return true;
	}
	public static void validateContenu(Partie partie) throws PartieContenuException {
		if(!isValide(partie.getLienDocument()) && !isValide(partie.getLienVideo())) {
			throw new PartieContenuException("une partie de leçon doit obligatoirement avoir au moins un pdf ou une vidéo");
		}
	}
}
