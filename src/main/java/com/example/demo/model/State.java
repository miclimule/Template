package com.example.demo.model;

public class State {

	private String name;
	
	private float recette;
	
	private float depense;
	
	private float beneficebrut;
	
	private float taxe;
	
	private float beneficenet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRecette() {
		return recette;
	}

	public void setRecette(float recette) {
		this.recette = recette;
	}

	public float getDepense() {
		return depense;
	}

	public void setDepense(float depense) {
		this.depense = depense;
	}

	public float getBeneficebrut() {
		return beneficebrut;
	}

	public void setBeneficebrut(float beneficebrut) {
		this.beneficebrut = beneficebrut;
	}

	public float getTaxe() {
		return taxe;
	}

	public void setTaxe(float taxe) {
		this.taxe = taxe;
	}

	public float getBeneficenet() {
		return beneficenet;
	}

	public void setBeneficenet(float beneficenet) {
		this.beneficenet = beneficenet;
	}

	public State(String name, float recette, float depense, float beneficebrut, float taxe, float beneficenet) {
		super();
		this.name = name;
		this.recette = recette;
		this.depense = depense;
		this.beneficebrut = beneficebrut;
		this.taxe = taxe;
		this.beneficenet = beneficenet;
	}

	public State() {
		super();
	}
	
	
	
}
