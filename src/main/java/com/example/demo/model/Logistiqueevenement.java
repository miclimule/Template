package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="logistiqueevenement")
public class Logistiqueevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int logistiqueid;
	
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


	public int getLogistiqueid() {
		return logistiqueid;
	}


	public void setLogistiqueid(int logistiqueid) {
		this.logistiqueid = logistiqueid;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public Logistiqueevenement() {
		super();
	}
	
	
}
