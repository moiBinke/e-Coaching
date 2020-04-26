package com.novem.cours.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Classe implements Serializable{
	@Column
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@Column
	private String nom;
	@OneToMany(mappedBy = "classe")
	private Collection<Matiere> matieres;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "professeur_id", nullable = true)
    @JsonIgnore
    private Professeur professeur;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cycle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Cycle cycle;
	
	
	public Cycle getCycle() {
		return cycle;
	}
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	public String getNom() {
		return nom;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Collection<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(Collection<Matiere> matiere) {
		this.matieres = matiere;
	}
    public Classe(){
    	
    }
    
    
}
