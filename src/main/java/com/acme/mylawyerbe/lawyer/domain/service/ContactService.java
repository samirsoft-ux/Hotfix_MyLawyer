package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactService {

    List<Contact> getAll();

    List<Contact> getAllByClientId(Long clientId);

    Page<Contact> getAllByClientId(Long clientId, Pageable pageable);

    List<Contact> getAllByLawyerId(Long lawyerId);

    Page<Contact> getAllByLawyerId(Long lawyerId, Pageable pageable);

    //crud

    //tambien se tiene que agregar el "Long lawyerId"
    Contact create(Long lawyerId, Long clientId, Contact contact);

    //tambien se tiene que agregar el "Long lawyerId"
    Contact update(Long lawyerId, Long clientId, Long contactId, Contact request);

    //tambien se tiene que agregar el "Long lawyerId"
    ResponseEntity<?> delete(Long contactId, Long clientId, Long lawyerId);
}
