package com.acme.mylawyerbe.lawyer.resource;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LawyerSpecialtyResource {

    private Long id;

    private LawyerResource lawyerResource;

    private SpecialtyResource specialtyResource;

}
