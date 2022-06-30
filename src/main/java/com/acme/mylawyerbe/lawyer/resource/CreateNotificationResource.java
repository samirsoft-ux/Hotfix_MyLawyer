package com.acme.mylawyerbe.lawyer.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationResource {

    private Date date;

    @NotNull
    @NotBlank
    @Size(max = 240)
    private String message;
}
