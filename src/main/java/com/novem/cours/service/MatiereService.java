package com.novem.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.CycleDao;
import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.ClasseException.ClasseHasNoCycleException;
import com.novem.cours.exceptions.MatiereException.MatiereClassNotFoundException;
@Service
public class MatiereService {
	
	@Autowired private ClasseDao classeDao;
	@Autowired MatiereDao matiereDao;
	
	public Matiere lierMatiereALaClasse(Long idClasse, Matiere matiere) throws MatiereClassNotFoundException {
		return classeDao.findById(idClasse).map(classe->{
		matiere.setClasse(classe);
		return matiereDao.save(matiere);
		}).orElseThrow(() -> new MatiereClassNotFoundException("la classe avec id: " + idClasse + " n'existe pas"));
	}

	public Matiere creerMatiere(Classe classe, Matiere matiere) throws MatiereClassNotFoundException {
		return lierMatiereALaClasse(classe.getId(),matiere)	;	
	}

}
