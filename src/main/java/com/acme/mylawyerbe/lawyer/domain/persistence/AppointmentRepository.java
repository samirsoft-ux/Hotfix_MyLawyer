package com.acme.mylawyerbe.lawyer.domain.persistence;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //verificar que clases se necesita, buscar porqué?
    //solo seria necesario buscar por id del cliente
    //findBy vs findAllBy
    //yo creo que es finAllBy porque es una lista
    List<Appointment> findByClientId(Long clientId);

    Page<Appointment> findByClientId(Long clientId, Pageable pageable);

    List<Appointment> findByLawyerId(Long lawyerId);

    Page<Appointment> findByLawyerId(Long lawyerId, Pageable pageable);

    Optional<Appointment> findByIdAndClientIdAndLawyerId(Long id, Long clientId, Long lawyerId);
}
