package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lieu")
public class Lieuplace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int lieuid;
	
	private int categorieplaceid;
	
	private int nbrplace;

	public Lieuplace() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLieuid() {
		return lieuid;
	}

	public void setLieuid(int lieuid) {
		this.lieuid = lieuid;
	}

	public int getCategorieplaceid() {
		return categorieplaceid;
	}

	public void setCategorieplaceid(int categorieplaceid) {
		this.categorieplaceid = categorieplaceid;
	}

	public int getNbrplace() {
		return nbrplace;
	}

	public void setNbrplace(int nbrplace) {
		this.nbrplace = nbrplace;
	}
}
