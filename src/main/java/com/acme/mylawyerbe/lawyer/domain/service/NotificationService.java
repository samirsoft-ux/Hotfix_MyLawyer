package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {

    //las entidades que tienen una relacion son distintas porque puedes buscarlos por ese atributo

    List<Notification> getAll();

    List<Notification> getAllByClientId(Long clientId);

    Page<Notification> getAllByClientId(Long clientId, Pageable pageable);

    List<Notification> getAllByLawyerId(Long lawyerId);

    Page<Notification> getAllByLawyerId(Long lawyerId, Pageable pageable);

    //crud
    //tambien se tiene que agregar el "Long lawyerId"
    Notification create(Long lawyerId, Long clientId, Notification notification);

    //tambien se tiene que agregar el "Long lawyerId"
    Notification update(Long lawyerId, Long clientId, Long notificationId, Notification request);

    //tambien se tiene que agregar el "Long lawyerId"
    ResponseEntity<?> delete(Long notificationId, Long clientId, Long lawyerId);

}
