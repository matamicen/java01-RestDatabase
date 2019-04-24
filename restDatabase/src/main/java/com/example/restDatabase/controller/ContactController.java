package com.example.restDatabase.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restDatabase.model.Contact;
import com.example.restDatabase.repository.ContactRepository;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {
	
	@Autowired
	ContactRepository daocontact;
	
	List<Contact> contactFound;
	
	
	@GetMapping
	public List<Contact> findAll(){
		System.out.println("paso por findAll");
	  return daocontact.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Contact> findById(@PathVariable long id){
		System.out.println("paso por /id");
		
	
	  return daocontact.findById(id)
	          .map(record -> ResponseEntity.ok().body(record))
	          .orElse(ResponseEntity.notFound().build());
		
	
	}
	
	@PostMapping
	public Contact create(@RequestBody Contact contact){
	    return daocontact.save(contact);
	    
	}
	
//	@PostMapping(path = {"/auth"})
//	public Contact create(@RequestHeader(name = "Authorization", required = true) String headerPersist, 
//			              @RequestBody Contact contact){
//	    return daocontact.save(contact);
	    
//	}
	
	 
	@PutMapping(value="/{id}")
	  public ResponseEntity<Contact> update(@PathVariable("id") long id,
	                                        @RequestBody Contact contact){
	    return daocontact.findById(id)
	        .map(record -> {
	            record.setName(contact.getName());
	            record.setEmail(contact.getEmail());
	            record.setPhone(contact.getPhone());
	            Contact updated = daocontact.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
	
	@DeleteMapping(path ={"/{id}"})
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
	    return daocontact.findById(id)
	        .map(record -> {
	            daocontact.deleteById(id);
	            return ResponseEntity.ok().build();
	        }).orElse(ResponseEntity.notFound().build());
	  }

}


