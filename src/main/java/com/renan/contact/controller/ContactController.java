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
import java.util.Optional;
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
    public ResponseEntity<?> getContacts(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String emails,
                                         @RequestParam(required = false) String phoneNumber,
                                         @RequestParam(required = false) String numbers) {

        if (name != null) {
            Optional<Contact> contact = contactService.getContactsByName(name);
            return ResponseEntity.ok(contact.map(List::of).orElseGet(List::of));
        } else if (email != null) {
            Optional<Contact> contact = contactService.getContactByEmail(email);
            return ResponseEntity.ok(contact.map(List::of).orElseGet(List::of));
        } else if (emails != null) {
            List<String> emailList = contactService.getAllEmails();
            return ResponseEntity.ok(emailList);
        } else if (phoneNumber != null) {
            Optional<Contact> contact = contactService.getContactByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(contact.map(List::of).orElseGet(List::of));
        } else if (numbers != null) {
            List<String> phoneNumberList = contactService.getAllPhoneNumbers();
            return ResponseEntity.ok(phoneNumberList);
        } else {
            List<Contact> contacts = contactService.getContacts();
            return ResponseEntity.ok(contacts);
        }
    }
    @PostMapping
    public ResponseEntity<Contact> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
        var savedContact = contactService.saveContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/by-name")
    public ResponseEntity<Contact> updateContactByName(@RequestBody @Valid ContactDTO contactDTO,
            @RequestParam String name) {
        var updateContact = contactService.updateContactByName(name, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactByName(@RequestBody @Valid ContactDTO contactDTO,
            @PathVariable Long id) {
        var updateContact = contactService.updateContactById(id, contactDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }
}
