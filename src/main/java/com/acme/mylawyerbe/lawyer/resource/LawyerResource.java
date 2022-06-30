package com.acme.mylawyerbe.lawyer.resource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LawyerResource {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private int age;

    private String email; //es necesario colocarlo como un valor que no se repita, por eso ¿cómo hacerlo en la bd?

    private int points;

    private String image;


}
