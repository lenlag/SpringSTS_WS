package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PseudoService {
	
	@Autowired
	PseudoDao dao;
	
	public PseudoService () {
		
	}
	public List<String> list () {
		return dao.myList();
		
	}
	
	public void delete(int index) {
		dao.delete(index);
	}

}
