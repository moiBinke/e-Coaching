package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novem.cours.dao.CycleDao;
import com.novem.cours.exceptions.CycleException.CycleNameExistException;

import static com.novem.cours.exceptions.CycleException.*;

@Component
public class CycleValidator {
	
	
	public static void validate(String nom,CycleDao cycleDao) throws CycleNameExistException {
		validateName( nom, cycleDao) ;
	}
	public static void validateName(String nom,CycleDao cycleDao) throws CycleNameExistException {
		if(nom!=null) {
			cycleDao.count();
			if(cycleDao.existsByNom(nom)) {
				throw new CycleNameExistException("Un cycle de meme nom Existe dejà");
			}
		}else {
			throw new CycleNameExistException("veuillez remplir le champ cycle");

		}
	}


}
