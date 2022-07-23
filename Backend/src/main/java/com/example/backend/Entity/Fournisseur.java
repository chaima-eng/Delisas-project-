package com.example.backend.Entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fournisseur extends User {
    private String nom_societe;
    private String adresse_societe;
    private String gouvernerat;




}
