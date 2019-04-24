package com.example.restDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restDatabase.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
