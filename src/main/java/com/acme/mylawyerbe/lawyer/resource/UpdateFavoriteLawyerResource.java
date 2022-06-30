package com.acme.mylawyerbe.lawyer.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFavoriteLawyerResource {

    private Long client_id;

    private Long lawyer_id;
}
