package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.LawyerSpecialtyService;
import com.acme.mylawyerbe.lawyer.mapping.LawyerSpecialtyMapper;
import com.acme.mylawyerbe.lawyer.resource.LawyerSpecialtyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping("/api/v1/lawyerSpecialties")
@Tag(name = "LawyerSpecialties", description = "Create, read, update and delete lawyer specialties")
public class LawyerSpecialtyController {

    private final LawyerSpecialtyService lawyerSpecialtyService;

    private final LawyerSpecialtyMapper mapper;

    public LawyerSpecialtyController(LawyerSpecialtyService lawyerSpecialtyService, LawyerSpecialtyMapper mapper) {
        this.lawyerSpecialtyService = lawyerSpecialtyService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all lawyer specialties")
    public Page<LawyerSpecialtyResource> getAllLawyerSpecialties(Pageable pageable){
        return mapper.modelListPage(lawyerSpecialtyService.getAll(), pageable);
    }

}
