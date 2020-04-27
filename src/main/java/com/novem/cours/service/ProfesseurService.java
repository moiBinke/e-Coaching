package com.novem.cours.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Professeur;
import com.novem.cours.entities.Role;
import com.novem.cours.exceptions.ProfesseurException;
import com.novem.cours.validators.PasswordEncryption;

@Service
public class ProfesseurService {

	@Autowired private ClasseDao classeDao;
	@Autowired private ProfesseurDao professeurDao;
	
	public Professeur creerProfesseur(Professeur professeur, String[] listeIdClasse) {
		professeur.setActive(true);
		professeur.setRole(Role.ROLE_PROFESSEUR);
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
		System.out.println("professeur:  "+professeur);
		String passwordDecrypted=PasswordEncryption.decrypt(professeur.getMotDePasse());
		professeur.setMotDePasse(passwordDecrypted);
		return professeur;

	}
	
	public Optional<Professeur> modifierProfesseur(Professeur professeur) throws ProfesseurException.ProfesseurEmailExist {
		Optional<Professeur> professeurInDataBase=this.professeurDao.findById(professeur.getId());
		if(professeurInDataBase.isPresent()) {
			if(!professeurInDataBase.get().getEmail().equals(professeur.getEmail())) {
				if(this.professeurDao.existsByEmail(professeur.getEmail())) {
					throw new ProfesseurException.ProfesseurEmailExist("Un professeur porte déjà la même adresse email");
				}
				else {
					professeurInDataBase.get().setEmail(professeur.getEmail());
				}
			}
			professeurInDataBase.get().setNom(professeur.getNom());;
			professeurInDataBase.get().setMotDePasse(PasswordEncryption.encrypt(professeur.getMotDePasse()));
			professeurInDataBase.get().setPrenom(professeur.getPrenom());
			professeurInDataBase.get().setPhoto(professeur.getPhoto());
			
			professeur = this.professeurDao.save(professeurInDataBase.get());
			String passwordDecrypted=PasswordEncryption.decrypt(professeurInDataBase.get().getMotDePasse());
			professeur.setMotDePasse(passwordDecrypted);
			return Optional.ofNullable(professeur);
		}
		return Optional.empty();
	}


}
