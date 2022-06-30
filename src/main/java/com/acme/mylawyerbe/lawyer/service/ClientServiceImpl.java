package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Client;
import com.acme.mylawyerbe.lawyer.domain.persistence.ClientRepository;
import com.acme.mylawyerbe.lawyer.domain.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    //crear el ENTITY
    private static final String ENTITY = "Client";

    //crear el repository
    private final ClientRepository clientRepository;

    //crear el validator

    private final Validator validator;

    //constructor
    public ClientServiceImpl(ClientRepository clientRepository, Validator validator) {
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new  ResourceNotFoundException(ENTITY, clientId));
    }

    @Override
    public Client getByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    @Override
    public Client getByLastName(String lastName){
        return clientRepository.findByLastName(lastName);
    }

    @Override
    public Client create(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //Name unique / pero para nuestro caso es necesario?
        //Que es lo que puso Diego?
        /*Client clientWithFirstName = clientRepository.findByFirstName(client.getFirstName());
        Client clientWithLastName = clientRepository.findByLastName(client.getLastName());
        if (clientWithFirstName != null || clientWithLastName != null)
            throw new ResourceValidationException(ENTITY,
                    "An client with the same name already exists.");*/

        //aca si le he puesto que el email sea unico
        Client clientWithEmail = clientRepository.findByEmail(client.getEmail());
        if (clientWithEmail != null)
            throw new ResourceValidationException(ENTITY,
                    "An client with the same email already exists.");

        return clientRepository.save(client);
    }

    @Override
    public Client update(Long clientId, Client request) {
        Set<ConstraintViolation<Client>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Client clientWithEmail = clientRepository.findByEmail(request.getEmail());

        if (clientWithEmail != null && !clientWithEmail.getId().equals(clientId))
            throw new ResourceValidationException(ENTITY,
                    "An client with the same email already exists.");

        return clientRepository.findById(clientId).map(client ->
                clientRepository.save(client
                        .withFirstName(request.getFirstName())
                        .withLastName(request.getLastName())
                        .withAddress(request.getAddress())
                        .withAge(request.getAge())
                        .withEmail(request.getEmail())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clientId));
    }

    @Override
    public ResponseEntity<?> delete(Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, clientId));
    }
}
