package com.example.demo.model;

import java.util.List;

public class InfoClass {

	String field;
	
	String type;
	
	String value;
	
	String balise;
	
	List<String> dataBalise;

	public String getBalise() {
		return balise;
	}

	public void setBalise(String balise) {
		this.balise = balise;
	}

	public List<String> getDataBalise() {
		return dataBalise;
	}

	public void setDataBalise(List<String> dataBalise) {
		this.dataBalise = dataBalise;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
