package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.AppointmentService;
import com.acme.mylawyerbe.lawyer.mapping.AppointmentMapper;
import com.acme.mylawyerbe.lawyer.resource.AppointmentResource;
import com.acme.mylawyerbe.lawyer.resource.CreateAppointmentResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateAppointmentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "acme")
@RestController
@RequestMapping("/api/v1/clients/{clientId}/appointments")
@Tag(name = "Appointments", description = "Create, read, update and delete clients")
public class ClientAppointmentsController {

    private final AppointmentService appointmentService;

    private final AppointmentMapper mapper;

    public ClientAppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all appointments by client ID")
    public Page<AppointmentResource> getAllAppointmentByClientId(@PathVariable Long clientId, Pageable pageable){
        return mapper.modelListPage(appointmentService.getAllByClientId(clientId), pageable);
    }

    /*@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create appointment by lawyer")
    public AppointmentResource createAppointment(@PathVariable Long lawyerId,
                                                 @PathVariable Long clientId,
                                                 @RequestBody CreateAppointmentResource resource){
        return mapper.toResource(appointmentService.create(lawyerId , clientId, mapper.toModel(resource)));
    }*/

    /*@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping("{appointmentId}")
    @Operation(summary = "Update appointment by ID")
    public AppointmentResource updateAppointment(@PathVariable Long lawyerId,
                                                 @PathVariable Long clientId,
                                                 @PathVariable Long appointmentId,
                                                 @RequestBody UpdateAppointmentResource resource){
        return mapper.toResource(appointmentService.update(lawyerId, clientId, appointmentId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId,
                                               @PathVariable Long clientId,
                                               @PathVariable Long lawyerId) {
        return appointmentService.delete(appointmentId, clientId, lawyerId);
    }*/
}
