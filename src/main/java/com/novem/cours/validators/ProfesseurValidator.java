package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailNotCorrect;

import static com.novem.cours.exceptions.ProfesseurException.*;

public class ProfesseurValidator {

	@Autowired private static ProfesseurDao professeurDao;
	
	public static void validateEmail(String email) throws ProfesseurEmailNotCorrect {
		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     if(!email.matches(regex)) {
	    	 throw new ProfesseurEmailNotCorrect("votre email n'est pas correct");
	     }
	}
	
	
}
