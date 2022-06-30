package com.acme.mylawyerbe.lawyer.domain.persistence;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    //verificar que clases se necesita, buscar porqué?
    //solo seria necesario buscar por id del cliente
    //findBy vs findAllBy
    //yo creo que es finAllBy porque es una lista
    List<Contact> findByClientId(Long clientId);

    Page<Contact> findByClientId(Long clientId, Pageable pageable);

    List<Contact> findByLawyerId(Long lawyerId);

    Page<Contact> findByLawyerId(Long lawyerId, Pageable pageable);

    Optional<Contact> findByIdAndClientIdAndLawyerId(Long id, Long clientId, Long lawyerId);
}
