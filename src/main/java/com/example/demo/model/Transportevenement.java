package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transportevenement")
public class Transportevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int transportid;
	
	private float consommation;

	

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



	public int getTransportid() {
		return transportid;
	}



	public void setTransportid(int transportid) {
		this.transportid = transportid;
	}



	public float getConsommation() {
		return consommation;
	}



	public void setConsommation(float consommation) {
		this.consommation = consommation;
	}



	public Transportevenement() {
		super();
	}
	
	
}
