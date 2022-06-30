package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.LawyerSpecialty;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerSpecialtyRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.SpecialtyRepository;
import com.acme.mylawyerbe.lawyer.domain.service.LawyerSpecialtyService;
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
public class LawyerSpecialtyServiceImpl implements LawyerSpecialtyService {

    private static final String ENTITY = "LawyerSpecialty";

    private final LawyerSpecialtyRepository lawyerSpecialtyRepository;

    private final Validator validator;

    private final LawyerRepository lawyerRepository;

    private final SpecialtyRepository specialtyRepository;

    public LawyerSpecialtyServiceImpl(LawyerSpecialtyRepository lawyerSpecialtyRepository, Validator validator, LawyerRepository lawyerRepository, SpecialtyRepository specialtyRepository) {
        this.lawyerSpecialtyRepository = lawyerSpecialtyRepository;
        this.validator = validator;
        this.lawyerRepository = lawyerRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<LawyerSpecialty> getAll() {
        return lawyerSpecialtyRepository.findAll();
    }

    @Override
    public List<LawyerSpecialty> getAllByLawyerId(Long lawyerId) {
        return lawyerSpecialtyRepository.findByLawyerId(lawyerId);
    }

    @Override
    public Page<LawyerSpecialty> getAllByLawyerId(Long lawyerId, Pageable pageable) {
        return lawyerSpecialtyRepository.findByLawyerId(lawyerId, pageable);
    }

    @Override
    public List<LawyerSpecialty> getAllBySpecialtyId(Long specialtyId) {
        return lawyerSpecialtyRepository.findBySpecialtyId(specialtyId);
    }

    @Override
    public Page<LawyerSpecialty> getAllBySpecialtyId(Long specialtyId, Pageable pageable) {
        return lawyerSpecialtyRepository.findBySpecialtyId(specialtyId, pageable);
    }

    @Override
    public LawyerSpecialty create(Long lawyerId, Long specialtyId, LawyerSpecialty lawyerSpecialty) {
        Set<ConstraintViolation<LawyerSpecialty>> violations = validator.validate(lawyerSpecialty);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            lawyerSpecialty.setLawyer(lawyer);
            return specialtyRepository.findById(specialtyId).map(specialty -> {
                lawyerSpecialty.setSpecialty(specialty);
                return lawyerSpecialtyRepository.save(lawyerSpecialty);
            }).orElseThrow(() -> new ResourceNotFoundException("Specialty", specialtyId));
        }).orElseThrow(() -> new ResourceNotFoundException("Lawyer",lawyerId));
        /*return specialtyRepository.findById(specialtyId).map(specialty -> {
            lawyerSpecialty.setSpecialty(specialty);
            return lawyerSpecialtyRepository.save(lawyerSpecialty);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialty", specialtyId));*/
    }

    @Override
    public LawyerSpecialty update(Long lawyerId, Long specialtyId, Long lawyerSpecialtyId, LawyerSpecialty request) {
        Set<ConstraintViolation<LawyerSpecialty>> violations = validator.validate(request);


        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!lawyerRepository.existsById(lawyerId))
            throw new ResourceNotFoundException("Lawyer",lawyerId);

        if (!specialtyRepository.existsById(specialtyId))
            throw new ResourceNotFoundException("Specialty", specialtyId);

        return lawyerSpecialtyRepository.findById(lawyerSpecialtyId).map(existingLawyerSpecialty ->
                lawyerSpecialtyRepository.save(existingLawyerSpecialty.withDate(request.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("Lawyer", "Client", lawyerId, specialtyId));
    }
    //TODO: Recuerda poner en orden los parametros en los controller
    @Override
    public ResponseEntity<?> delete(Long lawyerSpecialtyId, Long lawyerId, Long specialtyId) {
        return lawyerSpecialtyRepository.findByIdAndLawyerIdAndSpecialtyId(lawyerSpecialtyId, lawyerId, specialtyId).map(lawyerSpecialty -> {
            lawyerSpecialtyRepository.delete(lawyerSpecialty);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawyerSpecialtyId));
    }
}
