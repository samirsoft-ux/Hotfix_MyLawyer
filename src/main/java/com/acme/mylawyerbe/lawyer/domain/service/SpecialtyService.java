package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface SpecialtyService {
    List<Specialty> getAll();

    Page<Specialty> getAll(Pageable pageable);

    Specialty getById(Long specialtyId);

    Specialty getByName(String name);

    //Crud
    Specialty create(Specialty specialty);

    Specialty update(Long specialtyId, Specialty request);

    ResponseEntity<?> delete(Long specialtyId);
}
