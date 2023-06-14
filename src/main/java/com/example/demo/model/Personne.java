package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="personne")
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long maisonid;
	
	private String nom;
	
	private String prenom;
	
	private Date dateNaissance;

	
	public long getMaisonid() {
		return maisonid;
	}

	public void setMaisonid(long maisonid) {
		this.maisonid = maisonid;
	}

	public Personne(long maison, String nom, String prenom, String dateNaissance) {
		super();
		this.maisonid = maison;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = Date.valueOf(dateNaissance);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Personne() {
		super();
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", maisonid=" + maisonid + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + "]";
	}
	
	
}
