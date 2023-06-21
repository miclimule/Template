package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="billetvente")
public class Billetvente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int billetid;
	
	private Date datevente;
	
	private int nbrvente;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBilletid() {
		return billetid;
	}


	public void setBilletid(int billetid) {
		this.billetid = billetid;
	}


	public Date getDatevente() {
		return datevente;
	}


	public void setDatevente(Date datevente) {
		this.datevente = datevente;
	}


	public int getNbrvente() {
		return nbrvente;
	}


	public void setNbrvente(int nbrvente) {
		this.nbrvente = nbrvente;
	}


	public Billetvente() {
		super();
	}
	
	

}
