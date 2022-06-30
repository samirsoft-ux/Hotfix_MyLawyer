package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.domain.model.entity.LawyerSpecialty;
import com.acme.mylawyerbe.lawyer.resource.CreateLawyerSpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.LawyerSpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateLawyerSpecialtyResource;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LawyerSpecialtyMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public LawyerSpecialtyResource toResource(LawyerSpecialty model){
        return mapper.map(model, LawyerSpecialtyResource.class);
    }

    public Page<LawyerSpecialtyResource> modelListPage(List<LawyerSpecialty> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, LawyerSpecialtyResource.class), pageable, modelList.size());
    }

    public LawyerSpecialty toModel(CreateLawyerSpecialtyResource resource){
        return mapper.map(resource, LawyerSpecialty.class);
    }

    public LawyerSpecialty toModel(UpdateLawyerSpecialtyResource resource){
        return mapper.map(resource, LawyerSpecialty.class);
    }
}
