package com.example.restDatabase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.restDatabase.model.Contact;

public interface ContactDao extends JpaRepository<Contact, Long> {
	
	
	public List<Contact> findByName(String nombre);
}
