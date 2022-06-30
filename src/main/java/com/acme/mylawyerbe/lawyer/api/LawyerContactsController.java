package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.ContactService;
import com.acme.mylawyerbe.lawyer.mapping.ContactMapper;
import com.acme.mylawyerbe.lawyer.resource.ContactResource;
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
@RequestMapping("/api/v1/lawyers/{lawyerId}/contacts")
@Tag(name = "Contacts", description = "Create, read, update and delete lawyers")
public class LawyerContactsController {

    private final ContactService contactService;

    private final ContactMapper mapper;

    public LawyerContactsController(ContactService contactService, ContactMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all contacts by lawyer ID")
    public Page<ContactResource> getAllContactByLawyerId(@PathVariable Long lawyerId, Pageable pageable){
        return mapper.modelListPage(contactService.getAllByLawyerId(lawyerId), pageable);
    }

}
