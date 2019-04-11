package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PseudoDao {

	private MyObject object = new MyObject(1, "Привет", 100);
	private MyObject object1 = new MyObject(2, "Как дела", 150);
	private MyObject object2 = new MyObject(3, "Все хорошо", 200);
	private MyObject object3 = new MyObject(4, "А как у тебя", 220);
	private MyObject object4 = new MyObject(5, "Отлично", 450);
	private MyObject object5 = new MyObject(7, "Здорово!", 798);

	private List<MyObject> objList;

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

	public void update(int id, String chaine, int value) {
		for (MyObject m : objList) {
			m.setChaine(chaine);
			m.setId(id);
			m.setValue(value);
		}
	}
}
