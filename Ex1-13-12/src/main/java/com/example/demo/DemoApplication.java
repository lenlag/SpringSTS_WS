package com.example.demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner { // CommandLineRunner = > pour pouvoir afficher les résultats à l'écran
	
	
private static Log log = LogFactory.getLog(DemoApplication.class);

@Autowired
		PseudoService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		List<String> myList = service.list(); 
		
		myList.add("Test String");
		
		for(String s : myList) {
			log.info(s);
		}
		
		
	}

}

