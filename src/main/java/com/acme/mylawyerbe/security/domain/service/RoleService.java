package com.acme.mylawyerbe.security.domain.service;

import com.acme.mylawyerbe.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();
}