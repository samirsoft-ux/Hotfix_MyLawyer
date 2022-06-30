package com.acme.mylawyerbe.lawyer.resource;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientResource {

    //esta clase sirve para crear un nievo cliente
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Size(max = 240)
    private String address;

    //porqué lo estamos obviando el age?
    private int age;

    @NotNull
    @NotBlank
    @Size(max = 240)
    @Column(unique = true)
    private String email;
}
