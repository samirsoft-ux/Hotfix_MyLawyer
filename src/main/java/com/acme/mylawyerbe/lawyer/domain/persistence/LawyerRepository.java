package com.acme.mylawyerbe.lawyer.domain.persistence;


import com.acme.mylawyerbe.lawyer.domain.model.entity.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    Lawyer findByFirstName(String firstName);

    Lawyer findByLastName(String lastName);

    List<Lawyer> findAllByAge(int age);

    List<Lawyer> findAllByFirstName(String firstName);

    Lawyer findByEmail(String email);

}