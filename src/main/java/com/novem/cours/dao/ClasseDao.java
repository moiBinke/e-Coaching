package com.novem.cours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Classe;

@RepositoryRestResource
public interface ClasseDao extends JpaRepository<Classe,Long> {

	public boolean existsByNomAndCycleNom(String nomClasse, String nomCycle);
	
	public boolean existsByCycleNom(String nom);
}
