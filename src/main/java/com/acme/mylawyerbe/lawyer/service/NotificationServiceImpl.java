package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Notification;
import com.acme.mylawyerbe.lawyer.domain.persistence.ClientRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.NotificationRepository;
import com.acme.mylawyerbe.lawyer.domain.service.NotificationService;
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
public class NotificationServiceImpl implements NotificationService {

    private static final String ENTITY = "Notification";

    private final NotificationRepository notificationRepository;

    private final Validator validator;

    private final ClientRepository clientRepository;

    private final LawyerRepository lawyerRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, Validator validator, ClientRepository clientRepository, LawyerRepository lawyerRepository) {
        this.notificationRepository = notificationRepository;
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
    }

    //Aca las clases cambian un poco lo habra hecho en clase?

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getAllByClientId(Long clientId) {
        return notificationRepository.findByClientId(clientId);
    }

    @Override
    public Page<Notification> getAllByClientId(Long clientId, Pageable pageable) {
        return notificationRepository.findByClientId(clientId, pageable);
    }

    @Override
    public List<Notification> getAllByLawyerId(Long lawyerId) {
        return notificationRepository.findByLawyerId(lawyerId);
    }

    @Override
    public Page<Notification> getAllByLawyerId(Long lawyerId, Pageable pageable) {
        return notificationRepository.findByLawyerId(lawyerId, pageable);
    }

    @Override
    public Notification create(Long lawyerId, Long clientId, Notification notification) {
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            notification.setLawyer(lawyer);
            return clientRepository.findById(clientId).map(client -> {
                notification.setClient(client);
                return notificationRepository.save(notification);
            }).orElseThrow(() -> new ResourceNotFoundException("Client",clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Lawyer",lawyerId));

        /*return clientRepository.findById(clientId).map(client -> {
            notification.setClient(client);
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new ResourceNotFoundException("Client",clientId));*/
    }

    @Override
    public Notification update(Long lawyerId, Long clientId, Long notificationId, Notification request) {
        Set<ConstraintViolation<Notification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!lawyerRepository.existsById(lawyerId))
            throw new ResourceNotFoundException("Lawyer", lawyerId);

        if (!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client",clientId);

        return notificationRepository.findById(notificationId).map(existingNotification ->
                notificationRepository.save(existingNotification.withDate(request.getDate())))
                .orElseThrow();
    }

    @Override
    public ResponseEntity<?> delete(Long notificationId, Long clientId, Long lawyerId) {
        return notificationRepository.findByIdAndClientIdAndLawyerId(notificationId, clientId, lawyerId).map(notification -> {
            notificationRepository.delete(notification);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }
}
