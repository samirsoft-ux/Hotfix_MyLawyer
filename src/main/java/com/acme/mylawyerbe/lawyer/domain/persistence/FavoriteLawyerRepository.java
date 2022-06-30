package com.acme.mylawyerbe.lawyer.domain.persistence;

import com.acme.mylawyerbe.lawyer.domain.model.entity.FavoriteLawyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteLawyerRepository extends JpaRepository<FavoriteLawyer, Long> {
    //verificar que clases se necesita, buscar porqué?
    //solo seria necesario buscar por id del cliente
    //findBy vs findAllBy
    //yo creo que es finAllBy porque es una lista
    List<FavoriteLawyer> findByClientId(Long clientId);

    Page<FavoriteLawyer> findByClientId(Long clientId, Pageable pageable);

    List<FavoriteLawyer> findByLawyerId(Long lawyerId);

    Page<FavoriteLawyer> findByLawyerId(Long lawyerId, Pageable pageable);

    Optional<FavoriteLawyer> findByIdAndClientIdAndLawyerId(Long id, Long clientId, Long lawyerId);
}
