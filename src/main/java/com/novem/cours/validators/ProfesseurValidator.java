package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailExist;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailNotCorrect;

import static com.novem.cours.exceptions.ProfesseurException.*;

public class ProfesseurValidator {

	@Autowired private static ProfesseurDao professeurDao;
	
	public static void validateEmailSyntax(String email) throws ProfesseurEmailNotCorrect {
		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     if(!email.matches(regex)) {
	    	 throw new ProfesseurEmailNotCorrect("votre email n'est pas correct");
	     }
	}
	
	public static void validateEmailExistence(String email) throws ProfesseurEmailExist {
		if(professeurDao.existsByEmail(email)) {
			throw new ProfesseurEmailExist("un prof possède déjà le même mail");
		}
	}
	
}
