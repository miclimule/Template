package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sponsortevenement")
public class Sponsortevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int sponsortid;
	
	private float prix;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getEvenementid() {
		return evenementid;
	}



	public void setEvenementid(int evenementid) {
		this.evenementid = evenementid;
	}



	public int getSponsortid() {
		return sponsortid;
	}



	public void setSponsortid(int sponsortid) {
		this.sponsortid = sponsortid;
	}



	public float getPrix() {
		return prix;
	}



	public void setPrix(float prix) {
		this.prix = prix;
	}



	public Sponsortevenement() {
		super();
	}
	
	
}
