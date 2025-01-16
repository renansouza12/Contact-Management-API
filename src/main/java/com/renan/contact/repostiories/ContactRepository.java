package com.renan.contact.repostiories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.renan.contact.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByNameIgnoreCase(String name);

    Optional<Contact> findByName(String name);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhoneNumber(String phoneNumber);

    Page<Contact> findAll(Pageable pageable);

    void deleteByName(String name);
    
    long count();

    @Query("SELECT c.email FROM Contact c")
    List<String> findAllEmails();

    @Query("SELECT c.phoneNumber FROM Contact c")
    List<String> findAllPhoneNumbers();

}
