package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.domain.model.entity.Lawyer;
import com.acme.mylawyerbe.lawyer.resource.CreateLawyerResource;
import com.acme.mylawyerbe.lawyer.resource.LawyerResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateLawyerResource;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LawyerMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public LawyerResource toResource(Lawyer model){
        return mapper.map(model, LawyerResource.class);
    }

    public Page<LawyerResource> modelListPage(List<Lawyer> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, LawyerResource.class), pageable, modelList.size());
    }

    public Lawyer toModel(CreateLawyerResource resource){
        return mapper.map(resource, Lawyer.class);
    }

    public Lawyer toModel(UpdateLawyerResource resource){
        return mapper.map(resource, Lawyer.class);
    }
}
