package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PseudoDao {

private List<String> myList;


	public PseudoDao() {
		myList= new ArrayList<>();
		myList.add("String n1");
		myList.add("String n2");
		myList.add("String n3");
		myList.add("String n4");
		myList.add("String n5");
	}

	public List<String> myList() {
				
		return myList;
	}

	
	public void delete(int index) {
		myList.remove(index);
	}
}
