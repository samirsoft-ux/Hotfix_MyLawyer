package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.FavoriteLawyerService;
import com.acme.mylawyerbe.lawyer.mapping.FavoriteLawyerMapper;
import com.acme.mylawyerbe.lawyer.resource.FavoriteLawyerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping("/api/v1/clients/{clientId}/favoriteLawyers")
@Tag(name = "FavoriteLawyers", description = "Create, read, update and delete clients")
public class ClientFavoriteLawyersController {

    private final FavoriteLawyerService favoriteLawyerService;

    private final FavoriteLawyerMapper mapper;

    public ClientFavoriteLawyersController(FavoriteLawyerService favoriteLawyerService, FavoriteLawyerMapper mapper) {
        this.favoriteLawyerService = favoriteLawyerService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all favorite lawyers by client ID")
    public Page<FavoriteLawyerResource> getAllFavoriteLawyersByClientId(@PathVariable Long clientId, Pageable pageable){
        return mapper.modelListPage(favoriteLawyerService.getAllByClientId(clientId), pageable);
    }

}
