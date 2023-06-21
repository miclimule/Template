package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="materielevenement")
public class Materielevenement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int materielid;

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

	public int getMaterielid() {
		return materielid;
	}

	public void setMaterielid(int materielid) {
		this.materielid = materielid;
	}

	public Materielevenement() {
		super();
	}
	
	

}
