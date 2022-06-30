package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.NotificationService;
import com.acme.mylawyerbe.lawyer.mapping.NotificationMapper;
import com.acme.mylawyerbe.lawyer.resource.NotificationResource;
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
@RequestMapping("/api/v1/lawyers/{lawyerId}/notifications")
@Tag(name = "Notifications", description = "Create, read, update and delete lawyers")
public class LawyerNotificationsController {

    private final NotificationService notificationService;

    private final NotificationMapper mapper;

    public LawyerNotificationsController(NotificationService notificationService, NotificationMapper mapper) {
        this.notificationService = notificationService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all notifications by lawyer ID")
    public Page<NotificationResource> getAllNotificationByLawyerId(@PathVariable Long lawyerId, Pageable pageable){
        return mapper.modelListPage(notificationService.getAllByLawyerId(lawyerId), pageable);
    }

}
