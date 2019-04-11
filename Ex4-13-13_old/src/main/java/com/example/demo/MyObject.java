package com.example.demo;


public class MyObject {

	private int id;
	private String chaine;
	private int value;
	
	
		
	public MyObject() {
		
	}
	
		
	
	public MyObject(int id, String chaine, int value) {
		super();
		this.id = id;
		this.chaine = chaine;
		this.value = value;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChaine() {
		return chaine;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return id + ", " + chaine + ", " + value;
	}
	
	
	
	
}
