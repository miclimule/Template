package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sonorisationevenement")
public class Sonorisationevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int sonorisationid;
	
	private int duree;

	

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



	public int getSonorisationid() {
		return sonorisationid;
	}



	public void setSonorisationid(int sonorisationid) {
		this.sonorisationid = sonorisationid;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}



	public Sonorisationevenement() {
		super();
	}
	
	
	
}
