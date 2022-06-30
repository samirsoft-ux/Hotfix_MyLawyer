package com.acme.mylawyerbe.lawyer.service;

import com.acme.mylawyerbe.lawyer.domain.model.entity.FavoriteLawyer;
import com.acme.mylawyerbe.lawyer.domain.persistence.ClientRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.FavoriteLawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.persistence.LawyerRepository;
import com.acme.mylawyerbe.lawyer.domain.service.FavoriteLawyerService;
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
public class FavoriteLawyerServiceImpl implements FavoriteLawyerService {

    private static final String ENTITY = "FavoriteLawyer";

    private final FavoriteLawyerRepository favoriteLawyerRepository;

    private final Validator validator;

    private final ClientRepository clientRepository;

    private final LawyerRepository lawyerRepository;

    public FavoriteLawyerServiceImpl(FavoriteLawyerRepository favoriteLawyerRepository, Validator validator, ClientRepository clientRepository, LawyerRepository lawyerRepository) {
        this.favoriteLawyerRepository = favoriteLawyerRepository;
        this.validator = validator;
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
    }

    //Aca las clases cambian un poco lo habra hecho en clase?

    @Override
    public List<FavoriteLawyer> getAll() {
        return favoriteLawyerRepository.findAll();
    }

    @Override
    public List<FavoriteLawyer> getAllByClientId(Long clientId) {
        return favoriteLawyerRepository.findByClientId(clientId);
    }

    @Override
    public Page<FavoriteLawyer> getAllByClientId(Long clientId, Pageable pageable) {
        return favoriteLawyerRepository.findByClientId(clientId, pageable);
    }

    @Override
    public List<FavoriteLawyer> getAllByLawyerId(Long lawyerId) {
        return favoriteLawyerRepository.findByLawyerId(lawyerId);
    }

    @Override
    public Page<FavoriteLawyer> getAllByLawyerId(Long lawyerId, Pageable pageable) {
        return favoriteLawyerRepository.findByLawyerId(lawyerId, pageable);
    }

    @Override
    public FavoriteLawyer create(Long lawyerId, Long clientId, FavoriteLawyer favoriteLawyer) {
        Set<ConstraintViolation<FavoriteLawyer>> violations = validator.validate(favoriteLawyer);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            favoriteLawyer.setLawyer(lawyer);
            return clientRepository.findById(clientId).map(client -> {
                favoriteLawyer.setClient(client);
                return favoriteLawyerRepository.save(favoriteLawyer);
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Lawyer",lawyerId));
        /*return clientRepository.findById(clientId).map(client -> {
            favoriteLawyer.setClient(client);
            return favoriteLawyerRepository.save(favoriteLawyer);
        }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));*/
    }

    @Override
    public FavoriteLawyer update(Long lawyerId, Long clientId, Long favoriteLawyerId, FavoriteLawyer request) {
        Set<ConstraintViolation<FavoriteLawyer>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (!lawyerRepository.existsById(lawyerId))
            throw new ResourceNotFoundException("Lawyer", lawyerId);

        if (!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", clientId);

        return favoriteLawyerRepository.findById(favoriteLawyerId).map(existingFavoriteLawyer ->
                favoriteLawyerRepository.save(existingFavoriteLawyer.withDate(request.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException("Lawyer", "Client", lawyerId, clientId));
    }

    @Override
    public ResponseEntity<?> delete(Long favoriteLawyerId, Long clientId, Long lawyerId) {
        return favoriteLawyerRepository.findByIdAndClientIdAndLawyerId(favoriteLawyerId, clientId, lawyerId).map(favoriteLawyer -> {
            favoriteLawyerRepository.delete(favoriteLawyer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favoriteLawyerId));
    }
}
