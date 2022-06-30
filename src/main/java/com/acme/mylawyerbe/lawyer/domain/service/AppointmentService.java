package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    //opciones de lectura
    List<Appointment> getAll();

    List<Appointment> getAllByClientId(Long clientId);

    Page<Appointment> getAllByClientId(Long clientId, Pageable pageable);

    List<Appointment> getAllByLawyerId(Long lawyerId);

    Page<Appointment> getAllByLawyerId(Long lawyerId, Pageable pageable);

    //opciones crud

    //tambien se tiene que agregar el "Long lawyerId"
    Appointment create(Long lawyerId, Long clientId, Appointment appointment);

    //tambien se tiene que agregar el "Long lawyerId"
    Appointment update(Long lawyerId, Long clientId, Long appointmentId, Appointment request);

    //tambien se tiene que agregar el "Long lawyerId"
    ResponseEntity<?> delete(Long appointmentId, Long clientId, Long lawyerId);
}