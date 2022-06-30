package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.resource.CreateFavoriteLawyerResource;
import com.acme.mylawyerbe.lawyer.resource.FavoriteLawyerResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateFavoriteLawyerResource;
import com.acme.mylawyerbe.lawyer.domain.model.entity.FavoriteLawyer;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FavoriteLawyerMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public FavoriteLawyerResource toResource(FavoriteLawyer model){
        return  mapper.map(model, FavoriteLawyerResource.class);
    }

    public Page<FavoriteLawyerResource> modelListPage(List<FavoriteLawyer> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, FavoriteLawyerResource.class), pageable, modelList.size());
    }

    public FavoriteLawyer toModel(CreateFavoriteLawyerResource resource){
        return mapper.map(resource, FavoriteLawyer.class);
    }

    public FavoriteLawyer toModel(UpdateFavoriteLawyerResource resource){
        return mapper.map(resource, FavoriteLawyer.class);
    }
}
