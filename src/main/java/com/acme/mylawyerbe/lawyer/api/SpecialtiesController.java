package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.SpecialtyService;
import com.acme.mylawyerbe.lawyer.mapping.SpecialtyMapper;
import com.acme.mylawyerbe.lawyer.resource.CreateSpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.SpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateSpecialtyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping(value = "/api/v1/specialties", produces = "application/json")
@Tag(name = "Specialties", description = "Create, read, update and delete specialties")
public class SpecialtiesController {

    private final SpecialtyService specialtyService;

    private final SpecialtyMapper mapper;

    public SpecialtiesController(SpecialtyService specialtyService, SpecialtyMapper mapper) {
        this.specialtyService = specialtyService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all specialties")
    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public Page<SpecialtyResource> getAllSpecialties(@ParameterObject Pageable pageable){
        return mapper.modelListPage(specialtyService.getAll(), pageable);
    }

    @GetMapping("{specialtyId}")
    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @Operation(summary = "Get specialty by ID")
    public SpecialtyResource getSpecialtyById(@PathVariable Long specialtyId){
        return mapper.toResource(specialtyService.getById(specialtyId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create Specialty", responses = {
            @ApiResponse(description = "Specialty successfully created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SpecialtyResource.class)))
    })
    public ResponseEntity<SpecialtyResource> createSpecialty(@RequestBody CreateSpecialtyResource resource){
        return new ResponseEntity<>(mapper.toResource(specialtyService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{specialtyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update specialty")
    public SpecialtyResource updateSpecialty(@PathVariable Long specialtyId, @RequestBody UpdateSpecialtyResource resource){
        return mapper.toResource(specialtyService.update(specialtyId, mapper.toModel(resource)));
    }

    @DeleteMapping("{specialtyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete specialty")
    public ResponseEntity<?> deleteSpecialty(@PathVariable Long specialtyId){
        return specialtyService.delete(specialtyId);
    }

}
