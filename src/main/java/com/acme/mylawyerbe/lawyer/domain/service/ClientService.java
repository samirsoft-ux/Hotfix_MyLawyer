package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

// ACA DE SEGURO FALTA ALGUNA ANOTACIÓN PARA DECIRLE QUE ES UN SERVICIO
public interface ClientService {

    //Opciones de lectura
    List<Client> getAll();

    Page<Client> getAll(Pageable pageable);

    Client getById(Long clientId);

    Client getByFirstName(String firstName);

    Client getByLastName(String lastName);

    //Opciones de create, update, delete estas
    Client create(Client client);

    Client update(Long clientId, Client request);

    ResponseEntity<?> delete(Long clientId);
}
