package com.acme.mylawyerbe.lawyer.api;

import com.acme.mylawyerbe.lawyer.domain.service.NotificationService;
import com.acme.mylawyerbe.lawyer.mapping.NotificationMapper;
import com.acme.mylawyerbe.lawyer.resource.NotificationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Create, read, update and delete notifications")
public class NotificationsController {

    private final NotificationService notificationService;

    private final NotificationMapper mapper;


    public NotificationsController(NotificationService notificationService, NotificationMapper mapper) {
        this.notificationService = notificationService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all notifications")
    public Page<NotificationResource> getAllNotifications(Pageable pageable){
        return mapper.modelListPage(notificationService.getAll(), pageable);
    }
}
