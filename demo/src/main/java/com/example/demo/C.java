package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class C {
	
	int val = 0;

	public String  getMyString () {
		return "je suis C";
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	
}
