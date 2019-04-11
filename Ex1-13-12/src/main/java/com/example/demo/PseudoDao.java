package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PseudoDao {
	
	private List<String> myList = new ArrayList<>();
	
	public PseudoDao () {
		myList.add("String n1");
		myList.add("String n2");
		myList.add("String n3");
		myList.add("String n4");
		myList.add("String n5");
		
	}
	
	public List<String> myList () {
		return myList;
		
	}

}
