package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="artisteevenement")
public class Artisteevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int artisteid;
	
	private int evenementid;
	
	private int duree;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getArtisteid() {
		return artisteid;
	}



	public void setArtisteid(int artisteid) {
		this.artisteid = artisteid;
	}



	public int getEvenementid() {
		return evenementid;
	}



	public void setEvenementid(int evenementid) {
		this.evenementid = evenementid;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}



	public Artisteevenement() {
		super();
	}
	
	
}
