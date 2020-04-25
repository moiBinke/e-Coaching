package com.novem.cours.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Professeur implements Serializable{
	
	@Column
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private String email;
	@Column
	private String motDePasse;
	@Column
	private  Role role ;
	@Column
	private String photo;
	@Column
	private boolean active;
	@OneToMany(mappedBy = "professeur")
	private Collection<Classe> classes;
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<Classe> getClasses() {
		return classes;
	}
	public void setClasses(Collection<Classe> classes) {
		this.classes = classes;
	}
	public Professeur() {
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Professeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse="
				+ motDePasse + ", role=" + role + ", photo=" + photo + ", active=" + active + ", classes=" + classes
				+ "]";
	}
	
	

}
