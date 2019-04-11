package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
	
	@Autowired
	private B myB;
	
	public String getString() {
		return "A contient : " + myB.getMyString();
	}
	
	
}
