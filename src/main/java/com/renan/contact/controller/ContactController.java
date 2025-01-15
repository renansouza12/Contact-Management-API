package com.renan.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renan.contact.dtos.ContactDTO;
import com.renan.contact.models.Contact;
import com.renan.contact.servicies.ContactService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/contacts")
public class ContactController {
    
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getContacts();
    }

    @PostMapping
    public ResponseEntity<Contact> saveContact( @Valid @RequestBody  ContactDTO contactDTO){
        var savedContact = contactService.saveContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/by-name")
    public ResponseEntity<Contact> updateContactByName (@RequestBody @Valid ContactDTO contactDTO, @RequestParam String name){
        var updateContact = contactService.updateContactByName(name, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }   

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactByName (@RequestBody @Valid ContactDTO contactDTO, @PathVariable Long id){
        var updateContact = contactService.updateContactById(id, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }   
}
