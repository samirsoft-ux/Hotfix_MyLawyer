package com.acme.mylawyerbe.lawyer.resource;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecialtyResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
}
