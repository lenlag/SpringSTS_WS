package com.example.demo;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ex4Application implements CommandLineRunner {
	
	@Autowired
	private Frame frame;
	
	public static void main(String[] args) {
	    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Ex4Application.class)
	            .headless(false).run(args);
	}

	public void run(String... args) throws Exception {
	    EventQueue.invokeLater(() -> {
	    	frame.start();
	    	frame.setVisible(true);
	    });
	}

	

}

