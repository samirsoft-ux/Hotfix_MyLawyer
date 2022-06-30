package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Contact;
import com.acme.mylawyerbe.lawyer.domain.persistence.ClientRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.ContactRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.service.ContactService;
import com.acme.mylawyerbe.shared.exception.ResourceNotFoundException;
import com.acme.mylawyerbe.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {

    private static final String ENTITY = "Contact";

    private final ContactRepository contactRepository;

    private final Validator validator;

    private final ClientRepository clientRepository;

    private final LawyerRepository lawyerRepository;

    public ContactServiceImpl(ContactRepository contactRepository, Validator validator, ClientRepository clientRepository, LawyerRepository lawyerRepository) {
        this.contactRepository = contactRepository;
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
    }

    //Aca las clases cambian un poco lo habra hecho en clase?

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> getAllByClientId(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    @Override
    public Page<Contact> getAllByClientId(Long clientId, Pageable pageable) {
        return contactRepository.findByClientId(clientId, pageable);
    }

    @Override
    public List<Contact> getAllByLawyerId(Long lawyerId) {
        return contactRepository.findByLawyerId(lawyerId);
    }

    @Override
    public Page<Contact> getAllByLawyerId(Long lawyerId, Pageable pageable) {
        return contactRepository.findByLawyerId(lawyerId, pageable);
    }

    @Override
    public Contact create(Long lawyerId, Long clientId, Contact contact) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            contact.setLawyer(lawyer);
            return clientRepository.findById(clientId).map(client -> {
                contact.setClient(client);
                return contactRepository.save(contact);
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Lawyer", lawyerId));
        /*return clientRepository.findById(clientId).map(client -> {
            contact.setClient(client);
            return contactRepository.save(contact);
        }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));*/
    }

    @Override
    public Contact update(Long lawyerId, Long clientId, Long contactId, Contact request) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!lawyerRepository.existsById(lawyerId))
            throw new ResourceNotFoundException("Lawyer", lawyerId);

        if (!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", clientId);

        return contactRepository.findById(contactId).map(existingContact ->
                contactRepository.save(existingContact.withDate(request.getDate())))
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> delete(Long contactId, Long clientId, Long lawyerId) {
        return contactRepository.findByIdAndClientIdAndLawyerId(contactId, clientId, lawyerId).map(contact -> {
            contactRepository.delete(contact);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

}
