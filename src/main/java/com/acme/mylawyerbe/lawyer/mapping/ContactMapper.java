package com.acme.mylawyerbe.lawyer.mapping;

import com.acme.mylawyerbe.lawyer.resource.ContactResource;
import com.acme.mylawyerbe.lawyer.resource.CreateContactResource;
import com.acme.mylawyerbe.lawyer.resource.UpdateContactResource;
import com.acme.mylawyerbe.lawyer.domain.model.entity.Contact;
import com.acme.mylawyerbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ContactMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ContactResource toResource(Contact model){
        return mapper.map(model, ContactResource.class);
    }

    public Page<ContactResource> modelListPage(List<Contact> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ContactResource.class), pageable, modelList.size());
    }

    public Contact toModel(CreateContactResource resource){
        return mapper.map(resource, Contact.class);
    }

    public Contact toModel(UpdateContactResource resource){
        return mapper.map(resource, Contact.class);
    }
}
