package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Appointment;
import com.acme.mylawyerbe.lawyer.domain.persistence.AppointmentRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.ClientRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.service.AppointmentService;
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
public class AppointmentServiceImpl implements AppointmentService {

    private static final String ENTITY = "Appointment";

    private final AppointmentRepository appointmentRepository;

    private final Validator validator;

    private final ClientRepository clientRepository;

    private final LawyerRepository lawyerRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, Validator validator, ClientRepository clientRepository, LawyerRepository lawyerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
    }

    //Aca las clases cambian un poco lo habra hecho en clase?

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAllByClientId(Long clientId) {
        return appointmentRepository.findByClientId(clientId);
    }

    @Override
    public Page<Appointment> getAllByClientId(Long clientId, Pageable pageable) {
        return appointmentRepository.findByClientId(clientId, pageable);
    }

    @Override
    public List<Appointment> getAllByLawyerId(Long lawyerId) {
        return appointmentRepository.findByLawyerId(lawyerId);
    }

    @Override
    public Page<Appointment> getAllByLawyerId(Long lawyerId, Pageable pageable) {
        return appointmentRepository.findByLawyerId(lawyerId, pageable);
    }

    @Override
    public Appointment create(Long lawyerId, Long clientId, Appointment appointment) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //TODO: como retorno las 2 relaciones?
        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            appointment.setLawyer(lawyer);
            return clientRepository.findById(clientId).map(client -> {
                appointment.setClient(client);
                return appointmentRepository.save(appointment);
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Lawyer", lawyerId));

        /*return clientRepository.findById(clientId).map(client -> {
            appointment.setClient(client);
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));*/

    }

    @Override
    public Appointment update(Long lawyerId, Long clientId, Long appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!lawyerRepository.existsById(lawyerId))
            throw new ResourceNotFoundException("Lawyer", lawyerId);

        if (!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", clientId);

        return appointmentRepository.findById(appointmentId).map(existingAppointment ->
                appointmentRepository.save(existingAppointment.withDate(request.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("Lawyer", "Client", lawyerId, clientId));
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentId, Long clientId, Long lawyerId) {
        return appointmentRepository.findByIdAndClientIdAndLawyerId(appointmentId, clientId, lawyerId).map(appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }
}
