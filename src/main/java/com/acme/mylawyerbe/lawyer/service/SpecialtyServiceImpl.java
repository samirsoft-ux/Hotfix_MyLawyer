package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Specialty;
import com.acme.mylawyerbe.lawyer.domain.persistence.SpecialtyRepository;
import com.acme.mylawyerbe.lawyer.domain.service.SpecialtyService;
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
public class SpecialtyServiceImpl implements SpecialtyService {

    private static final String ENTITY = "Specialty";

    private final SpecialtyRepository specialtyRepository;

    private final Validator validator;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, Validator validator) {
        this.specialtyRepository = specialtyRepository;
        this.validator = validator;
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Specialty getById(Long specialtyId) {
        return specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }

    @Override
    public Specialty getByName(String name) {
        return specialtyRepository.findByName(name);
    }

    @Override
    public Specialty create(Specialty specialty) {
        Set<ConstraintViolation<Specialty>> violations = validator.validate(specialty);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Long specialtyId, Specialty request) {
        Set<ConstraintViolation<Specialty>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return specialtyRepository.findById(specialtyId).map(specialty ->
                specialtyRepository.save(specialty
                        .withName(request.getName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }

    @Override
    public ResponseEntity<?> delete(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialtyRepository.delete(specialty);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }
}
