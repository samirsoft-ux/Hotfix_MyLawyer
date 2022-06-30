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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lawyers/{lawyerId}/appointments")
@Tag(name = "Appointments", description = "Create, read, update and delete lawyers")
public class LawyerAppointmentsController {

    private final AppointmentService appointmentService;

    private final AppointmentMapper mapper;


    public LawyerAppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all appointments by lawyer ID")
    public Page<AppointmentResource> getAllAppointmentByLawyerId(@PathVariable Long lawyerId, Pageable pageable){
        return mapper.modelListPage(appointmentService.getAllByLawyerId(lawyerId), pageable);
    }

    /*@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Long lawyerId,
                                                 @PathVariable Long clientId,
                                                 @PathVariable Long appointmentId,
                                                 @RequestBody UpdateAppointmentResource resource){
        return mapper.toResource(appointmentService.update(lawyerId, clientId, appointmentId, mapper.toModel(resource)));
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId,
                                               @PathVariable Long clientId,
                                               @PathVariable Long lawyerId){
        return appointmentService.delete(appointmentId, clientId, lawyerId);
    }*/

}
