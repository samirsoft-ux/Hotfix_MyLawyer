package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.FavoriteLawyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FavoriteLawyerService {

    List<FavoriteLawyer> getAll();

    List<FavoriteLawyer> getAllByClientId(Long clientId);

    Page<FavoriteLawyer> getAllByClientId(Long clientId, Pageable pageable);

    List<FavoriteLawyer> getAllByLawyerId(Long lawyerId);

    Page<FavoriteLawyer> getAllByLawyerId(Long lawyerId, Pageable pageable);

    //crud

    //tambien se tiene que agregar el "Long lawyerId"
    FavoriteLawyer create(Long lawyerId, Long clientId, FavoriteLawyer favoriteLawyer);

    //tambien se tiene que agregar el "Long lawyerId"
    FavoriteLawyer update(Long lawyerId, Long clientId, Long favoriteLawyerId, FavoriteLawyer request);

    //tambien se tiene que agregar el "Long lawyerId"
    ResponseEntity<?> delete(Long favoriteLawyerId, Long clientId, Long lawyerId);
}
