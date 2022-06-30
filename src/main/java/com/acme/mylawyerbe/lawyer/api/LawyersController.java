package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.LawyerService;
import com.acme.mylawyerbe.lawyer.mapping.LawyerMapper;
import com.acme.mylawyerbe.lawyer.resource.CreateLawyerResource;
import com.acme.mylawyerbe.lawyer.resource.LawyerResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateLawyerResource;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/lawyers", produces = "application/json")
@Tag(name = "Lawyers", description = "Create, read, update and delete lawyers")
public class LawyersController {

    private final LawyerService lawyerService;

    private final LawyerMapper mapper;

    public LawyersController(LawyerService lawyerService, LawyerMapper mapper) {
        this.lawyerService = lawyerService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all lawyers")
    public Page<LawyerResource> getAllLawyers(@ParameterObject Pageable pageable){
        return mapper.modelListPage(lawyerService.getAll(), pageable);
    }

    @GetMapping("{lawyerId}")
    @Operation(summary = "Get lawyer by ID")
    public  LawyerResource getLawyerId(@PathVariable Long lawyerId){
        return mapper.toResource(lawyerService.getById(lawyerId));
    }

    @PostMapping
    @Operation(summary = "Create Lawyer", responses = {
            @ApiResponse(description = "Lawyer successfully created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LawyerResource.class)))
    })
    public ResponseEntity<LawyerResource> createLawyer(@RequestBody CreateLawyerResource resource){
        return new ResponseEntity<>(mapper.toResource(lawyerService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{lawyerId}")
    @Operation(summary = "Update Lawyer")
    public LawyerResource updateLawyer(@PathVariable Long lawyerId, @RequestBody UpdateLawyerResource resource){
        return mapper.toResource(lawyerService.update(lawyerId, mapper.toModel(resource)));
    }

    @DeleteMapping("{lawyerId}")
    @Operation(summary = "Delete Lawyer")
    public ResponseEntity<?> deleteLawyer(@PathVariable Long lawyerId){
        return lawyerService.delete(lawyerId);
    }

}
