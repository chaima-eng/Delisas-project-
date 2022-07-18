package com.example.backend.Entity;



import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Colis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idColis;
    private int code_a_bar;
    private int qrCode;
    private String nom_complet_client;
    private int num_tel;
    private int num_tel_2;


    private LocalDate date_livraison;
    private String delegation_client;
    private String adresse_client;
    private int nb_piece;
    private int largeur;
    private int longueur;
    private String hauteur;
    private String mode_paiement;
    private String service_colis;
    private int code_colis;
    private String designation_colis;
    private String remarque;
    @Enumerated(EnumType.STRING)
    private Etat_colis etat_colis ;
    @OneToOne(cascade = {CascadeType.ALL})
    private AnomalieColis anomalieColis;
    private int compteur_anomalie;
    private String localisation_colis;












}
