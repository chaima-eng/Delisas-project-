package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String code_a_bar;
    private int qrCode;
    private String nom_complet_client;
    private int num_tel;
    private int num_tel_2;
    private float total;
    private int quantite;

    private LocalDate date_livraison;
    private String delegation_client;
    private String adresse_client;
    private int nb_piece;
    private int largeur;
    private int longueur;
    private int hauteur;
    private String mode_paiement;
    private String service_colis;
    private int code_colis;
    private String designation_colis;
    private String remarque;
    @Enumerated(EnumType.STRING)
    private Etat_colis etat_colis ;
    @OneToOne(cascade = {CascadeType.ALL})
    private Anomalie_colis anomalieColis;
    private int compteur_anomalie;
    private String localisation_colis;

    @JsonIgnore
    @ManyToOne
    private Hub hub;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Fournisseur> fournisseurs = new HashSet<>();

    @JsonIgnore
    @ManyToMany( cascade = CascadeType.ALL)
    private Set<Pickup> pickups;
	
    











}