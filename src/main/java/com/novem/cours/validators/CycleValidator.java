package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.CycleDao;
import com.novem.cours.exceptions.CycleException.CycleNameExistException;

import static com.novem.cours.exceptions.CycleException.*;

public class CycleValidator {
	
	@Autowired private static CycleDao cycleDao;
	
	public static void validateName(String nom) throws CycleNameExistException {
		if(cycleDao.existsByNom(nom)) {
			throw new CycleNameExistException("Un cycle de meme nom Existe dejà");
		}
	}


}
