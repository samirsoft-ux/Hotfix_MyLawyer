package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.LawyerSpecialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LawyerSpecialtyService {

    List<LawyerSpecialty> getAll();

    List<LawyerSpecialty> getAllByLawyerId(Long lawyerId);

    Page<LawyerSpecialty> getAllByLawyerId(Long lawyerId, Pageable pageable);

    List<LawyerSpecialty> getAllBySpecialtyId(Long specialtyId);

    Page<LawyerSpecialty> getAllBySpecialtyId(Long specialtyId, Pageable pageable);

    //Crud
    LawyerSpecialty create(Long lawyerId, Long specialtyId, LawyerSpecialty lawyerSpecialty);

    LawyerSpecialty update(Long lawyerId, Long specialtyId, Long lawyerSpecialtyId, LawyerSpecialty request);

    ResponseEntity<?> delete(Long lawyerSpecialtyId, Long lawyerId, Long specialtyId);
}
