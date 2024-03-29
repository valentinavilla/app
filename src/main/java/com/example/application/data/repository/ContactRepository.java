package com.example.application.data.repository;

import com.example.application.data.entity.Contact;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    @Query("select c from Contact c " +
    "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
    "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))") 
     List<Contact> search(@Param("searchTerm") String searchTerm);
     
    //@Query("select c from Contact c where c.IDPamac=IDPamac")
    //List<Contact> search(@Param("IDP") Integer IDPamac);

    @Query("select c from Contact c where c.IDPamac = :IDP")  
    Contact search1(@Param("IDP") Integer IDPamac);
}
