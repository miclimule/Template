package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lieuevenement")
public class Lieuevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private int lieuid;
	
	private Date dateevenement;
		
	private float prix;

	private int heure;
	
	private int minute;

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

	public int getLieuid() {
		return lieuid;
	}

	public void setLieuid(int lieuid) {
		this.lieuid = lieuid;
	}

	public Date getDateevenement() {
		return dateevenement;
	}

	public void setDateevenement(Date dateevenement) {
		this.dateevenement = dateevenement;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public Lieuevenement() {
		super();
	}
	
	
}
