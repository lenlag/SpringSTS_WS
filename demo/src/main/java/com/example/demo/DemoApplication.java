package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner { //c'est un thread, c'est pour ça qu'il y a un run

	
	@Autowired // collecter les annotations
	private A myA;
	
	
	@Autowired
	private C myC;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { ///... nbre indéfini des paramètres
		System.out.println("Hello, World!");
		System.out.println(myA.getString());
		myC.setVal(10); // on intialise la valeur de C à 10
		System.out.println(myC.getVal()); // 10
		
		System.out.println(myA.getString());
		
		C aC = new C(); // nouvel objet
		System.out.println("" + aC.getVal()); // la valeur est zero
	}

}

