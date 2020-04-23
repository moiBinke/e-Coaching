package com.novem.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.novem.cours.dao.ClasseDao;
import com.novem.cours.dao.CycleDao;
import com.novem.cours.dao.ProfesseurDao;
import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Cycle;
import com.novem.cours.entities.Professeur;
import com.novem.cours.exceptions.ClasseException.ClasseHasNoCycleException;
@Service
public class ClasseService {
	
	@Autowired private CycleDao cycleDao;
	@Autowired private ClasseDao classeDao;
	@Autowired private ProfesseurDao professeurDao;
	
	public Classe lierClasseAuCycle(Long idCycle, Classe classe) throws ClasseHasNoCycleException {
		 return cycleDao.findById(idCycle).map(cycle -> {
			 Professeur professeur =new Professeur(); 
			 professeurDao.save(professeur);
			 classe.setCycle(cycle);  
			 classe.setProfesseur(professeur);
	         return classeDao.save(classe);
	        }).orElseThrow(() -> new ClasseHasNoCycleException("le cycle avec id: " + idCycle + " n'existe pas"));
	}

	public Classe creerClasse(Classe classe, Cycle cycle) throws ClasseHasNoCycleException {
		return lierClasseAuCycle(cycle.getId(), classe);
	}

}
