package com.novem.cours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Classe;
import com.novem.cours.entities.Matiere;

@RepositoryRestResource
public interface MatiereDao extends JpaRepository<Matiere, Long> {

	public boolean existsByNomAndClasse(String nom, Classe classe);

}
