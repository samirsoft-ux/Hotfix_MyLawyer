package com.acme.mylawyerbe.lawyer.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResource {

    private Long id;

    private Date date;

    private String message;

    private ClientResource clientResource;

    private LawyerResource lawyerResource;
}
