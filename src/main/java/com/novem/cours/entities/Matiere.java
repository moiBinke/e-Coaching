package com.novem.cours.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
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
public class Matiere  implements Serializable{
	
	@Column
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@Column
	private String nom;
	@OneToMany(mappedBy = "matiere",cascade = CascadeType.REMOVE)
	private Collection<Lecon> lecons;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Classe classe;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Lecon> getLecons() {
		return lecons;
	}
	public void setLecons(Collection<Lecon> lecons) {
		this.lecons = lecons;
	}
	public Matiere() {
		
	}

}
