package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.resource.CreateNotificationResource;
import com.acme.mylawyerbe.lawyer.resource.NotificationResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateNotificationResource;
import com.acme.mylawyerbe.lawyer.domain.model.entity.Notification;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class NotificationMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public NotificationResource toResource(Notification model){
        return mapper.map(model, NotificationResource.class);
    }

    public Page<NotificationResource> modelListPage(List<Notification> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, NotificationResource.class), pageable, modelList.size());
    }

    public Notification toModel(CreateNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }

    public Notification toModel(UpdateNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }
}
