package com.example.demo.model;

public class Vbillet {

	private int id;
	
	private int idlp;

	private int nbrplace;
	
	private float prix;
	
	private String categorie;
	
	private int taxe;
	
	private int nbrvente;
	
	public int getIdlp() {
		return idlp;
	}

	public void setIdlp(int idlp) {
		this.idlp = idlp;
	}

	public int getNbrvente() {
		return nbrvente;
	}

	public void setNbrvente(int nbrvente) {
		this.nbrvente = nbrvente;
	}

	public int getTaxe() {
		return taxe;
	}

	public void setTaxe(int taxe) {
		this.taxe = taxe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrplace() {
		return nbrplace;
	}

	public void setNbrplace(int nbrplace) {
		this.nbrplace = nbrplace;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Vbillet() {
		super();
	}
	
	
	
}
