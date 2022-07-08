package com.example.application.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Contact;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;

@Service
public class CrmService {
    private final ContactRepository contactRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository,
                      StatusRepository statusRepository) { 
        this.contactRepository = contactRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) { 
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact) {
        if (contact == null) { 
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(contact);
    }


    public List<com.example.application.data.entity.Status> findAllStatuses(){
        return statusRepository.findAll();
    }
}