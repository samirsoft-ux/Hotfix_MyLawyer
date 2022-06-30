package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.ClientService;
import com.acme.mylawyerbe.lawyer.mapping.ClientMapper;
import com.acme.mylawyerbe.lawyer.resource.ClientResource;
import com.acme.mylawyerbe.lawyer.resource.CreateClientResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateClientResource;
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
@RequestMapping(value = "/api/v1/clients", produces = "application/json")
@Tag(name = "Clients", description = "Create, read, update and delete clients")
public class ClientsController {

    private final ClientService clientService;

    private final ClientMapper mapper;

    public ClientsController(ClientService clientService, ClientMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    public Page<ClientResource> getAllClients(@ParameterObject Pageable pageable){
        return mapper.modelListPage(clientService.getAll(), pageable);
    }

    @GetMapping("{clientId}")
    @Operation(summary = "Get a client by id")
    public ClientResource getClientById(@PathVariable Long clientId){
        return mapper.toResource(clientService.getById(clientId));
    }

    @PostMapping
    @Operation(summary = "Create Client", responses = {
            @ApiResponse(description = "Client successfully created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResource.class)))
    })
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource){
        return new ResponseEntity<>(mapper.toResource(clientService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{clientId}")
    @Operation(summary = "Update Client")
    public ClientResource updateClient(@PathVariable Long clientId, @RequestBody UpdateClientResource resource){
        return mapper.toResource(clientService.update(clientId, mapper.toModel(resource)));
    }

    @DeleteMapping("{clientId}")
    @Operation(summary = "Delete Client")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId){
        return clientService.delete(clientId);
    }

}
