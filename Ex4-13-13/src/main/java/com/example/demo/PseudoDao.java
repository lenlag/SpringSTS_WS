package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PseudoDao {

	private MyObject object = new MyObject(1, "I learn Java", 100);
	private MyObject object1 = new MyObject(2, "SQL stands for Structured Query Language", 150);
	private MyObject object2 = new MyObject(3, "I learn Spring", 200);
	private MyObject object3 = new MyObject(4, "Swing is a dated tool for creating desktop applications", 220);
	private MyObject object4 = new MyObject(5, "Java Script is a popular scripting language", 450);
	private MyObject object5 = new MyObject(7, "DAO is an object that provides an abstract interface to some type of database", 798);

	private List<MyObject> objList;
//	private MyObject myobject;

	public PseudoDao() {
		objList = new ArrayList<>();
		objList.add(object);
		objList.add(object1);
		objList.add(object2);
		objList.add(object3);
		objList.add(object4);
		objList.add(object5);
	}

	public List<MyObject> myList() {
		return objList;
	}

	public void delete(int index) {

		objList.remove(index);

	}

	public void create(MyObject newObject) {
		objList.add(newObject);
	}

	public void update(int index, MyObject newObject) {
		objList.get(index).setId(newObject.getId());
		objList.get(index).setChaine(newObject.getChaine());
		objList.get(index).setValue(newObject.getValue());
	}
	
	public List<MyObject> findBy(String string) {
		List<MyObject> searchList = new ArrayList<>();
		for (MyObject m : objList) {
			if (m.getChaine().contains(string))
				searchList.add(m);
		}
		
		return searchList;
	
		
		
	}
}
