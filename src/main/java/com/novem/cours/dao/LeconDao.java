package com.novem.cours.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Lecon;
import com.novem.cours.entities.Matiere;

@RepositoryRestResource
public interface LeconDao extends JpaRepository<Lecon, Long> {

	public boolean existsByNomAndMatiere(String nom, Matiere matiere);

	public Collection<Lecon> findByMatiereId(Long idMatiere);

}
