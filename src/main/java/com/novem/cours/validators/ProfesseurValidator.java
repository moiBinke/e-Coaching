package com.novem.cours.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Professeur;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassDejaAffecte;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassNotFoun;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailExist;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailNotCorrect;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurWrongPassword;

import static com.novem.cours.exceptions.ProfesseurException.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfesseurValidator {

	
	private static  Pattern pattern;
    private static Matcher matcher;
  
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%+!]).{8,40})";
  
    public static void validate(Professeur professeur, ClasseDao classeDao, String[] listeIdClasse,ProfesseurDao professeurDao) throws ProfesseurEmailNotCorrect,
    ProfesseurEmailExist, ProfesseurWrongPassword, ProfesseurClassNotFoun, ProfesseurClassDejaAffecte {
		validateEmailSyntax(professeur.getEmail());
		validateEmailExistence(professeur.getEmail(), professeurDao);
		validatePassword(professeur.getMotDePasse());
		lesClassesExistent(listeIdClasse, classeDao);
		lesClassesNeSontPasAffectees(listeIdClasse, classeDao);
	}
    
    public static boolean isRightPassWord(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
 
    }
    
	public static void validatePassword(String password) throws ProfesseurWrongPassword {
	
		  // On accepte ssi
	    String pwd="minimum 8 caractères,"
	    		+ " 1 carcatère miniscul,"
	    		+ "1 carcatère MAJUSCUL,"
	    		+ "et 1 carcatère spécial sont obligatoire";
		if(password==null) {
			throw  new ProfesseurWrongPassword("Le mot de passe doit être fort pour votre sécurité : "+pwd);

		}
		pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        boolean x=  matcher.matches();
		if(!x) {
			throw  new ProfesseurWrongPassword("Le mot de passe doit être fort pour votre sécurité : "+pwd);
		}
		
	}
	public static void validateEmailSyntax(String email) throws ProfesseurEmailNotCorrect {
 		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     if(email==null) {
	    		throw new ProfesseurEmailNotCorrect("l'email est obligatoire");
	     }
	    	if( !email.matches(regex) ) { 
	    		throw new ProfesseurEmailNotCorrect("votre email n'est pas correct");
	     }
	}
	
	public static void validateEmailExistence(String email,ProfesseurDao professeurDao) throws ProfesseurEmailExist {
		if(professeurDao.existsByEmail(email)) {
			throw new ProfesseurEmailExist("un prof possède déjà le même mail");
		}
	}
	
	
	
	private static void  lesClassesExistent(String [] listeIdClasse, ClasseDao classeDao) throws ProfesseurClassNotFoun {
		if(listeIdClasse!=null && listeIdClasse.length!=0) {
			for(String idClasse: listeIdClasse) { 
				if(!(classeDao.existsById(new Long(idClasse)))) {
					throw new ProfesseurClassNotFoun("la classe avec id +"+idClasse +"n'existe pas!");
				}
			}
		}
		else {
			throw new ProfesseurClassNotFoun("Il faut affecter le prof à au moins  une classe!");

		}
	}
	
	private static void  lesClassesNeSontPasAffectees(String [] listeIdClasse,ClasseDao classeDao) throws ProfesseurClassDejaAffecte {
		for(String idClasse: listeIdClasse) {
			if((classeDao.getOne(new Long(idClasse)).getProfesseur().getEmail()!=null )) {
				Classe classe=classeDao.getOne(new Long(idClasse));
				String message = "la classe "+classe.getNom()+ "a déjà "+classe.getProfesseur().getNom()+ " comme professeur";
				message+= classe.getProfesseur().isActive()?" et est activé":" Mais n'est pas activé";
				throw new ProfesseurClassDejaAffecte(message);
			}
		}
	}
}
