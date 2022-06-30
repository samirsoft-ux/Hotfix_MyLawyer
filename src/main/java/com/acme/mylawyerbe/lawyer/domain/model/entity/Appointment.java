package com.acme.mylawyerbe.lawyer.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date; //tiene alguna validación

    //falta las relaciones
    //la clase donde se coloca la relación ya no extiende de Audit Model
    // 1 cliente tiene muchas appointment
    //clientId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    //lawyerId
    //con el atributo "createdAt" es suficiente?
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lawyer_id", nullable = false)
    @JsonIgnore
    private Lawyer lawyer;

}
