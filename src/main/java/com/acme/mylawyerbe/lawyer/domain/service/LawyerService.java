package com.acme.mylawyerbe.lawyer.domain.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Lawyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LawyerService {
    List<Lawyer> getAll();

    Page<Lawyer> getAll(Pageable pageable);

    Lawyer getById(Long lawyerId);

    Lawyer getByFirstName(String firstName);

    Lawyer getByLastName(String lastName);

    //Crud
    Lawyer create(Lawyer lawyer);

    Lawyer update(Long lawyerId, Lawyer request);

    ResponseEntity<?> delete(Long lawyerId);

}
