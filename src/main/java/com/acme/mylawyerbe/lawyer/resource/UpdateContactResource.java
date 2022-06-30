package com.acme.mylawyerbe.lawyer.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactResource {

    private Long client_id;

    private Long lawyer_id;
}
