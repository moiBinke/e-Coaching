package com.novem.cours.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Professeur;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassDejaAffecte;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurClassNotFoun;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailExist;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurEmailNotCorrect;
import com.novem.cours.exceptions.ProfesseurException.ProfesseurWrongPassword;
import com.novem.cours.service.ProfesseurService;
import com.novem.cours.validators.ProfesseurValidator;

@RestController
@RequestMapping("/professeurs")
@CrossOrigin("*")
public class ProfesseurController {

	@Autowired private ProfesseurService professeurService;
	@Autowired private ProfesseurDao professeurDao;
	@Autowired private ClasseDao classeDao;
	
	
	@GetMapping("/findAll")
	public Collection<Professeur>findAll() {
		return professeurDao.findAll();
	}
	
	@PostMapping("/creer/{lesIdClasses}")
	public ResponseEntity<Object>  creerProfesseur(@PathVariable("lesIdClasses")String lesIdClasses, 
			@RequestBody Professeur professeur) {
		Map<String, String>errors=new HashMap<String, String>();
		String [] listeIdClasse = lesIdClasses.split(",");
		try {
			ProfesseurValidator.validate(professeur, classeDao, listeIdClasse,professeurDao);
			return new ResponseEntity<Object>(professeurService.creerProfesseur(professeur,listeIdClasse),HttpStatus.OK);
		} catch (ProfesseurEmailNotCorrect e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);			
		} catch (ProfesseurEmailExist e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurWrongPassword e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurClassNotFoun e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProfesseurClassDejaAffecte e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/{email}/{password}")
	public Professeur getProfesseur(@PathVariable("email")String email,@PathVariable("password") String password) {
		System.out.println(password+"  "+email);
		return professeurService.findByEmailAndPassword(email,password);
	}
	
	@PatchMapping("/modifier")
	public ResponseEntity<Object> modifierIdentifiant(@RequestBody Professeur professeur){
		System.out.println(professeur);
		Map<String, String>errors=new HashMap<String, String>();
		try {
			ProfesseurValidator.validateEmailSyntax(professeur.getEmail());
			//ProfesseurValidator.validatePassword(professeur.getMotDePasse());
			return new ResponseEntity<Object>(professeurService.modifierProfesseur(professeur),HttpStatus.OK);
		} catch (ProfesseurEmailNotCorrect e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);			
		} catch (ProfesseurEmailExist e) {
			errors.put("erreur", e.getMessage());
			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
//		catch (ProfesseurWrongPassword e) {
//			errors.put("erreur", e.getMessage());
//			return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
//		} 
	}
	
	@GetMapping("/{idProfesseur}/classes")
	public Optional<Collection<Classe>> chercherLesClassesDeCeProf(@PathVariable("idProfesseur")Long idProfesseur){
		Optional<Professeur> professeur= professeurDao.findById(idProfesseur);
		if(professeur.isPresent()) {
			return Optional.ofNullable(professeur.get().getClasses());
		}
		return Optional.empty();
	}
	
	@GetMapping("/chercherByClasse/{idClasse}")
	public Optional<Professeur> chercherByClasse(@PathVariable("idClasse")Long idClasse){
		Classe classe=classeDao.getOne(idClasse);
		if(classe.getProfesseur()==null || (classe.getProfesseur().getEmail()==null && classe.getProfesseur().getMotDePasse()==null)) {
			return Optional.empty();
		}
		Professeur professeur=classe.getProfesseur();
		//Pour ne pas envoyer toutes les données:
		Professeur professeurReduit=new Professeur();
		professeurReduit.setActive(professeur.isActive());
		professeurReduit.setEmail(professeur.getEmail());
		professeurReduit.setId(professeur.getId());
		professeurReduit.setNom(professeur.getNom());
		professeurReduit.setPrenom(professeur.getPrenom());
		professeurReduit.setPhoto(professeur.getPhoto());
		return Optional.of(professeurReduit);
	}
	
	@DeleteMapping("/supprimer/{idProf}")
	public void supprimerById(@PathVariable("idProf")Long idProf) {
		
		Professeur professeur=professeurDao.getOne(idProf);
		professeur.setEmail(null);
		professeur.setMotDePasse(null);
		professeur.setNom(null);
		professeur.setPrenom(null);
		professeur.setPhoto(null);
		professeur.setActive(false);
		professeurDao.save(professeur);
	}
}
