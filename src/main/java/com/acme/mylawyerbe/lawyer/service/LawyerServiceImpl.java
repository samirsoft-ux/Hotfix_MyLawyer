package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Lawyer;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.service.LawyerService;
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
public class LawyerServiceImpl implements LawyerService {

    private static final String ENTITY = "Lawyer";

    private final LawyerRepository lawyerRepository;

    private final Validator validator;


    public LawyerServiceImpl(LawyerRepository lawyerRepository, Validator validator) {
        this.lawyerRepository = lawyerRepository;
        this.validator = validator;
    }

    @Override
    public List<Lawyer> getAll() {
        return lawyerRepository.findAll();
    }

    @Override
    public Page<Lawyer> getAll(Pageable pageable) {
        return lawyerRepository.findAll(pageable);
    }

    @Override
    public Lawyer getById(Long lawyerId) {
        return lawyerRepository.findById(lawyerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawyerId));
    }

    @Override
    public Lawyer getByFirstName(String firstName) {
        return lawyerRepository.findByFirstName(firstName);
    }

    @Override
    public Lawyer getByLastName(String lastName) {
        return lawyerRepository.findByLastName(lastName);
    }

    @Override
    public Lawyer create(Lawyer lawyer) {
        Set<ConstraintViolation<Lawyer>> violations = validator.validate(lawyer);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Lawyer lawyerWithEmail = lawyerRepository.findByEmail(lawyer.getEmail());
        if (lawyerWithEmail != null)
            throw new ResourceValidationException(ENTITY,
                    "An lawyer with the same email already exists.");

        return lawyerRepository.save(lawyer);
    }

    @Override
    public Lawyer update(Long lawyerId, Lawyer request) {
        Set<ConstraintViolation<Lawyer>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Lawyer lawyerWithEmail = lawyerRepository.findByEmail(request.getEmail());

        if (lawyerWithEmail != null && !lawyerWithEmail.getId().equals(lawyerId))
            throw new ResourceValidationException(ENTITY,
                    "An lawyer with the same email already exists.");
        return lawyerRepository.findById(lawyerId).map(lawyer ->
                lawyerRepository.save(lawyer
                        .withFirstName(request.getFirstName())
                        .withLastName(request.getLastName())
                        .withAddress(request.getAddress())
                        .withAge(request.getAge())
                        .withEmail(request.getEmail())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawyerId));
    }

    @Override
    public ResponseEntity<?> delete(Long lawyerId) {
        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            lawyerRepository.delete(lawyer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawyerId));
    }
}
