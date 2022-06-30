package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.ContactService;
import com.acme.mylawyerbe.lawyer.mapping.ContactMapper;
import com.acme.mylawyerbe.lawyer.resource.ContactResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/contacts")
@Tag(name = "Contacts", description = "Create, read, update and delete contacts")
public class ContactsController {

    private final ContactService contactService;

    private final ContactMapper mapper;

    public ContactsController(ContactService contactService, ContactMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all contacts")
    public Page<ContactResource> getAllContacts(Pageable pageable){
        return mapper.modelListPage(contactService.getAll(), pageable);
    }
}
