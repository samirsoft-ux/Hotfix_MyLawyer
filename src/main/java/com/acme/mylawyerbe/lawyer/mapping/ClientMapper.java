package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.resource.ClientResource;
import com.acme.mylawyerbe.lawyer.resource.CreateClientResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateClientResource;
import com.acme.mylawyerbe.lawyer.domain.model.entity.Client;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClientMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    //Mapeo de los objetos

    //
    public ClientResource toResource(Client model){
        return mapper.map(model, ClientResource.class);
    }

    //
    public Page<ClientResource> modelListPage(List<Client> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ClientResource.class), pageable, modelList.size());
    }

    //Crear
    public Client toModel(CreateClientResource resource){
        return mapper.map(resource, Client.class);
    }

    //Update
    public Client toModel(UpdateClientResource resource){
        return mapper.map(resource, Client.class);
    }
}
