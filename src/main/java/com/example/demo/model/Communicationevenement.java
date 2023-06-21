package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="communicationevenement")
public class Communicationevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int communicationid;

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

	public int getCommunicationid() {
		return communicationid;
	}

	public void setCommunicationid(int communicationid) {
		this.communicationid = communicationid;
	}

	public Communicationevenement() {
		super();
	}
	
	
}
