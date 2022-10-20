package com.example.backend.Entity;

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
public class Fournisseur extends User {


    private String nomSociete;
    private String adresseSociete;
    private String gouverneratSociete;
    private String deligationSociete;
    private String imageSociete;
    private String gouverneratLivraison;
    private String deligationLivraison;
    private int prixLivrasion;
    private int prixRetour;
    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;
    @Temporal(TemporalType.DATE)

    private Date dateDebutFin;


    @OneToMany
    @JsonIgnore
    private Set<Ticket> tickets;


}
