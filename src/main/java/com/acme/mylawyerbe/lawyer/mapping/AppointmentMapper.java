package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.resource.AppointmentResource;
import com.acme.mylawyerbe.lawyer.resource.CreateAppointmentResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateAppointmentResource;
import com.acme.mylawyerbe.lawyer.domain.model.entity.Appointment;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AppointmentMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public AppointmentResource toResource(Appointment model){
        return mapper.map(model, AppointmentResource.class);
    }

    public Page<AppointmentResource> modelListPage(List<Appointment> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, AppointmentResource.class), pageable, modelList.size());
    }

    public Appointment toModel(CreateAppointmentResource resource){
        return mapper.map(resource, Appointment.class);
    }

    public Appointment toModel(UpdateAppointmentResource resource){
        return mapper.map(resource, Appointment.class);
    }
}
