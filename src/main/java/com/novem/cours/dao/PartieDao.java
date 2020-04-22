package com.novem.cours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Partie;

@RepositoryRestResource
public interface PartieDao extends JpaRepository<Partie, Long> {

	public boolean existsByNomAndLecon(String nom, Lecon lecon);

}
