package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maison")
public class Maison {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String address;
	
	private int post;
	
	private boolean isclean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public boolean isIsclean() {
		return isclean;
	}

	public void setIsclean(boolean isclean) {
		this.isclean = isclean;
	}

	public Maison(String address, int post, boolean isclean) {
		super();
		this.address = address;
		this.post = post;
		this.isclean = isclean;
	}

	public Maison() {
		super();
	}
	
	
}
