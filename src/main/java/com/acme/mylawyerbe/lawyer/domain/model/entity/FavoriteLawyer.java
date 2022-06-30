package com.acme.mylawyerbe.lawyer.domain.model.entity;

import com.acme.mylawyerbe.shared.domain.model.AuditModel;
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
@Table(name = "favoriteLawyers")
public class FavoriteLawyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributo que le estoy poniendo de mas para que me deje utilizar el resource
    private Date date;

    //falta las relaciones
    //la clase donde se coloca la relación ya no extiende de Audit Model
    // 1 cliente tiene muchas favorite
    //clientId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    // de 1 a muchos lawyers a Favorite
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lawyer_id", nullable = false)
    @JsonIgnore
    private Lawyer lawyer;
}
