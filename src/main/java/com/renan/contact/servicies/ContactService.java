package com.renan.contact.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renan.contact.dtos.ContactDTO;
import com.renan.contact.models.Contact;
import com.renan.contact.repostiories.ContactRepository;

import jakarta.validation.Valid;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getContactsByName(String name) {
        return contactRepository.findByNameIgnoreCase(name);
    }

    public List<Contact> getContactByEmail(String email) {
        return contactRepository.findByNameIgnoreCase(email);
    }

    public List<String> getAllEmails() {
        return contactRepository.findAllEmails();
    }

    public Optional<Contact> getContactByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber);
    }

    public List<String> getAllPhoneNumbers() {
        return contactRepository.findAllPhoneNumbers();
    }

    public Contact saveContact(@Valid ContactDTO contactDTO) {
        var contact = new Contact();
        BeanUtils.copyProperties(contactDTO, contact);
        return contactRepository.save(contact);
    }

    public Contact updateContactByName(String name, @Valid ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactRepository.findByName(name);
        if (existingContact.isPresent()) {
            BeanUtils.copyProperties(contactDTO, existingContact.get());
            return contactRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Contact not found with name: " + name);
        }
    }

    public Contact updateContactById(Long id, @Valid ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            BeanUtils.copyProperties(contactDTO, existingContact.get());
            return contactRepository.save(existingContact.get());
        } else {
            throw new RuntimeException("Contact not found with id: " + id);
        }
    }


    public long countContacts() {
        return contactRepository.count();
    }
}
