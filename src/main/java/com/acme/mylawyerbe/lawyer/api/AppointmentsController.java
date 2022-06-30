package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.AppointmentService;
import com.acme.mylawyerbe.lawyer.mapping.AppointmentMapper;
import com.acme.mylawyerbe.lawyer.resource.AppointmentResource;
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
@RequestMapping(value = "/api/v1/appointments")
@Tag(name = "Appointments", description = "Create, read, update and delete appointments")
public class AppointmentsController {

    private final AppointmentService appointmentService;

    private final AppointmentMapper mapper;

    public AppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all appointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable){
        return mapper.modelListPage(appointmentService.getAll(), pageable);
    }

}
