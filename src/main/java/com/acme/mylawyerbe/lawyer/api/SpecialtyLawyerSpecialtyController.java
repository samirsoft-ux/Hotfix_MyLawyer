package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.LawyerSpecialtyService;
import com.acme.mylawyerbe.lawyer.mapping.LawyerSpecialtyMapper;
import com.acme.mylawyerbe.lawyer.resource.LawyerSpecialtyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/specialties/{specialtyId}/lawyerSpecialties")
@Tag(name = "LawyerSpecialties", description = "Create, read, update and delete specialties")
public class SpecialtyLawyerSpecialtyController {

    private final LawyerSpecialtyService lawyerSpecialtyService;

    private final LawyerSpecialtyMapper mapper;

    public SpecialtyLawyerSpecialtyController(LawyerSpecialtyService lawyerSpecialtyService, LawyerSpecialtyMapper mapper) {
        this.lawyerSpecialtyService = lawyerSpecialtyService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all lawyer specialties by specialty ID")
    public Page<LawyerSpecialtyResource> getAllLawyerSpecialtyBySpecialtyId(@PathVariable Long specialtyId, Pageable pageable){
        return mapper.modelListPage(lawyerSpecialtyService.getAllBySpecialtyId(specialtyId), pageable);
    }

}
