package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
	@Autowired
	private C myC;
	
	public String  getMyString () {
		return "je suis B " + myC.getMyString();
	}

}
