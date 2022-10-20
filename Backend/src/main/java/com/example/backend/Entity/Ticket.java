package com.example.backend.Entity;

import javax.persistence.GeneratedValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;
    private String objet;
    private String descreption;
    @Enumerated(EnumType.STRING)
    private  EtatTicket etatticket ;

    @OneToMany
    @JsonIgnore
    private Set<Colis> colis;

    @ManyToOne
    @JsonIgnore
    private Fournisseur fournisseur;

    @ManyToOne
    @JsonIgnore
    private Personnel personnel;











}
