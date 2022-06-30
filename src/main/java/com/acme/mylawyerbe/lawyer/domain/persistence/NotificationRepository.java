package com.acme.mylawyerbe.lawyer.domain.persistence;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //verificar que clases se necesita, buscar porqué?
    //solo seria necesario buscar por id del cliente
    //yo creo que es finAllBy porque es una lista
    List<Notification> findByClientId(Long clientId);

    Page<Notification> findByClientId(Long clientId, Pageable pageable);

    List<Notification> findByLawyerId(Long lawyerId);

    Page<Notification> findByLawyerId(Long lawyerId, Pageable pageable);

    Optional<Notification> findByIdAndClientIdAndLawyerId(Long id, Long clientId, Long lawyerId);
}
