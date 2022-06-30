package com.acme.mylawyerbe.lawyer.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ClientResource {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private int age;

    private String email;
}