package com.novem.cours.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.novem.cours.dao.LeconDao;
import com.novem.cours.dao.PartieDao;
import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Partie;
import com.novem.cours.exceptions.PartieException.PartieLeconNotFound;;

@Service
public class PartieService {

	@Autowired private PartieDao partieDao;
	@Autowired private LeconDao leconDao;
	
	public Partie lierPartieALaLecon(Long idLecon,Partie partie) throws PartieLeconNotFound {
		return leconDao.findById(idLecon).map(lecon->{
			partie.setLecon(lecon);
			return partieDao.save(partie);
		}).orElseThrow(()->new PartieLeconNotFound("la leçon avec id: " + idLecon + " n'existe pas"));
	}
	
	public Partie creerPartie(Optional<Lecon> lecon, Partie partie) throws PartieLeconNotFound {
		return lierPartieALaLecon(lecon.get().getId(), partie);
	}

}
