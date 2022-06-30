package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Specialty;
import com.acme.mylawyerbe.lawyer.resource.CreateSpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.SpecialtyResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateSpecialtyResource;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SpecialtyMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public SpecialtyResource toResource(Specialty model){
        return mapper.map(model, SpecialtyResource.class);
    }

    public Page<SpecialtyResource> modelListPage(List<Specialty> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, SpecialtyResource.class), pageable, modelList.size());
    }

    public Specialty toModel(CreateSpecialtyResource resource){
        return mapper.map(resource, Specialty.class);
    }

    public Specialty toModel(UpdateSpecialtyResource resource){
        return mapper.map(resource, Specialty.class);
    }
}