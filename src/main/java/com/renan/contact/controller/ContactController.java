package com.renan.contact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renan.contact.dtos.ContactDTO;
import com.renan.contact.models.Contact;
import com.renan.contact.servicies.ContactService;

import jakarta.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countContacts() {
        return ResponseEntity.ok(contactService.countContacts());
    }

    @GetMapping
    public ResponseEntity<?> getContacts(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String emails,
                                         @RequestParam(required = false) String phoneNumber,
                                         @RequestParam(required = false) String numbers) {

        if (name != null) {
            return getContactResponse(contactService.getContactByName(name));
        } else if (email != null) {
            return getContactResponse(contactService.getContactByEmail(email));
        } else if (emails != null) {
            return ResponseEntity.ok(contactService.getAllEmails());
        } else if (phoneNumber != null) {
            return getContactResponse(contactService.getContactByPhoneNumber(phoneNumber));
        } else if (numbers != null) {
            return ResponseEntity.ok(contactService.getAllPhoneNumbers());
        } else {
            return ResponseEntity.ok(contactService.getContacts());
        }
    }

    private ResponseEntity<?> getContactResponse(Optional<Contact> contact) {
        return ResponseEntity.ok(contact.map(List::of).orElseGet(List::of));
    }

    @PostMapping
    public ResponseEntity<Contact> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
        logger.info("Received contact data: {}", contactDTO);

        var savedContact = contactService.saveContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/contact")
    public ResponseEntity<Contact> updateContactByName(@RequestBody @Valid ContactDTO contactDTO,
                                                       @RequestParam String name) {
        var updateContact = contactService.updateContactByName(name, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactId(@RequestBody @Valid ContactDTO contactDTO,
                                                   @PathVariable Long id) {
        var updateContact = contactService.updateContactById(id, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }

    @DeleteMapping("/contact/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name) {
        contactService.deleteByName(name);
        return ResponseEntity.status(HttpStatus.OK).body("Contact " + name + " deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Contact " + id + " deleted");
    }
}
