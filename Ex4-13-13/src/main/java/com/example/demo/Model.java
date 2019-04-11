package com.example.demo;

import javax.swing.AbstractListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Model extends AbstractListModel<MyObject> {
	
	/**
	 * 
	 */
	@Autowired
	private PseudoService service;
	
		
	private static final long serialVersionUID = 1L;
	

	public Model() { // constr-r doit rester vide
		
	}


	@Override
	public MyObject getElementAt(int index) {
		return service.list().get(index);
	}

	@Override
	public int getSize() {
		return service.list().size();
	}




}
