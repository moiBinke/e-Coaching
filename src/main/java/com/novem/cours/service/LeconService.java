package com.novem.cours.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novem.cours.dao.LeconDao;
import com.novem.cours.dao.MatiereDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Matiere;
import com.novem.cours.exceptions.LeconException;
import com.novem.cours.exceptions.LeconException.LeconMatiereNotFound;

@Service
public class LeconService {
	@Autowired private LeconDao leconDao;
	@Autowired private MatiereDao matiereDao;
	
	public Lecon lierLeconALaMatiere(Long idMatiere,Lecon lecon) throws LeconMatiereNotFound {
		return matiereDao.findById(idMatiere).map(matiere->{
			lecon.setMatiere(matiere);
			return leconDao.save(lecon);
		}).orElseThrow(()->new LeconException.LeconMatiereNotFound("la matière avec id: " + idMatiere + " n'existe pas"));
	}
	public Lecon creerLecon(Optional<Matiere> matiere, Lecon lecon) throws LeconMatiereNotFound {
		return lierLeconALaMatiere(matiere.get().getId(),lecon) ;
	}

}
