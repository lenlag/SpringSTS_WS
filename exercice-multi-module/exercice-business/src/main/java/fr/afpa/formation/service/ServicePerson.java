package fr.afpa.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.formation.dao.PersonDao;
import fr.afpa.formation.entity.Person;

@Service
public class ServicePerson {
	
	@Autowired 
	PersonDao dao;
	
	public ServicePerson() {
		
	}
	
	public List<Person> findAll() {
		List<Person> findAll = dao.list();
		
		return findAll;
	}

}
