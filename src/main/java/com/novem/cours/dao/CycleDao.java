package com.novem.cours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.novem.cours.entities.Cycle;

@RepositoryRestResource
public interface CycleDao extends JpaRepository<Cycle, Long>{

	public boolean existsByNom(String nom);

}
