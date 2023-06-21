package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="billetevenement")
public class Billetevenement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int evenementid;
	
	private float prix;
	
	private int categorieplaceid;

	private int taxe;
	
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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getCategorieplaceid() {
		return categorieplaceid;
	}

	public void setCategorieplaceid(int categorieplaceid) {
		this.categorieplaceid = categorieplaceid;
	}

	public int getTaxe() {
		return taxe;
	}

	public void setTaxe(int taxe) {
		this.taxe = taxe;
	}

	public Billetevenement() {
		super();
	}
	
	
}
