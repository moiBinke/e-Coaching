package com.novem.cours.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cycle implements Serializable{
	
	@Column
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@Column
	private String nom;
	@OneToMany(mappedBy = "cycle")
	private Collection<Classe> classes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Classe> getClasses() {
		return classes;
	}
	public void setClasses(Collection<Classe> classe) {
		this.classes = classe;
	}
	
	public Cycle() {
		
	}
	

}
