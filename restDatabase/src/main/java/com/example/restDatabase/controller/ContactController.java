package com.example.restDatabase.controller;


import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<Object> findAll(){
		System.out.println("paso por findAll");
		
		List<Contact> contactsFound = daocontact.findAll();

		if (contactsFound.size()>0) {	
			//System.out.println("encontro:"+con.getName() + con.getEmail());
			 for(Contact con: contactsFound)
		     {
				 System.out.println("name:"+con.getName() + con.getEmail());
		     }
		     
			 JSONObject obj = new JSONObject();
			 

		      obj.put("error", 0);
		      obj.put("results", contactsFound);
		      
		      
		      

		return ResponseEntity.ok().body(obj.toString());
		}else {
			JSONObject obj = new JSONObject();
			 
		   
			obj.put("error", 1);
			obj.put("description", "No data found");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
		}
//	  return daocontact.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Contact> findById(@PathVariable long id){
		System.out.println("paso por /id");
		
	Contact con = daocontact.findById(id).orElse(null);

	

	if (con!=null) {
	 System.out.println("encontro:"+con.getName() + con.getEmail());
	
	
	return ResponseEntity.ok().body(con);
	}else return ResponseEntity.notFound().build();
		
	
//	  return daocontact.findById(id)
//	          .map(record -> ResponseEntity.ok().body(record))
//	          .orElse(ResponseEntity.notFound().build());
		
	
	}
	// public ResponseEntity<List<Object>> findByName(@PathVariable String name){
	
	@GetMapping(path = {"/name/{name}"})
	public ResponseEntity<Object> findByName(@PathVariable String name){
		System.out.println("paso por /name");
		
	List<Contact> contactsFound = daocontact.findByName(name);

	if (contactsFound.size()>0) {	
		//System.out.println("encontro:"+con.getName() + con.getEmail());
		 for(Contact con: contactsFound)
	     {
			 System.out.println("name:"+con.getName() + con.getEmail());
	     }
	     
		 JSONObject obj = new JSONObject();
		 

	      obj.put("error", 0);
	      obj.put("results", contactsFound);
	      
	      
	      

	return ResponseEntity.ok().body(obj.toString());
	}else {
		JSONObject obj = new JSONObject();
		 
	   
		obj.put("error", 1);
		obj.put("description", "No data found");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
	}
		
		
		//return ResponseEntity.notFound().build();
		
	
	
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
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


