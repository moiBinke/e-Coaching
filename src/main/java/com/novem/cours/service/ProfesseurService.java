package com.novem.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Professeur;
import com.novem.cours.validators.PasswordEncryption;

@Service
public class ProfesseurService {

	@Autowired private ClasseDao classeDao;
	@Autowired private ProfesseurDao professeurDao;
	
	public Professeur creerProfesseur(Professeur professeur, String[] listeIdClasse) {
		String passwordEncrypted=PasswordEncryption.encrypt(professeur.getMotDePasse());
		professeur.setMotDePasse(passwordEncrypted);
		System.out.println(professeur.getMotDePasse());
		professeur=professeurDao.save(professeur);
		for(String idClasse: listeIdClasse) {
			Classe classe= classeDao.getOne(new Long(idClasse));
			Professeur professeurInDataBase = classe.getProfesseur();
			System.out.println(professeurInDataBase);
			classe.setProfesseur(professeur);
			classeDao.save(classe);
			professeurDao.delete(professeurInDataBase);
		}
		return professeur;
	}

	public Professeur findByEmailAndPassword(String email, String password) {
	    password= PasswordEncryption.encrypt(password);
		Professeur professeur=professeurDao.findByEmailAndMotDePasse(email,password);
		String passwordDecrypted=PasswordEncryption.decrypt(professeur.getMotDePasse());
		professeur.setMotDePasse(passwordDecrypted);
		return professeur;

	}

}
