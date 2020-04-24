package com.novem.cours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Professeur;

@RepositoryRestResource
public interface ProfesseurDao extends JpaRepository<Professeur,Long> {

	public boolean existsByEmail(String email);

	public Professeur findByEmailAndMotDePasse(String email, String password);

}
