package com.acme.mylawyerbe.lawyer.resource;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateSpecialtyResource {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
}
