package com.acme.mylawyerbe.lawyer.domain.persistence;

import com.acme.mylawyerbe.lawyer.domain.model.entity.LawyerSpecialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawyerSpecialtyRepository extends JpaRepository<LawyerSpecialty, Long> {

    List<LawyerSpecialty> findByLawyerId(Long lawyerId);

    Page<LawyerSpecialty> findByLawyerId(Long lawyerId, Pageable pageable);

    List<LawyerSpecialty> findBySpecialtyId(Long specialtyId);

    Page<LawyerSpecialty> findBySpecialtyId(Long specialtyId, Pageable pageable);

    Optional<LawyerSpecialty> findByIdAndLawyerIdAndSpecialtyId(Long id, Long lawyerId, Long specialtyId);

}