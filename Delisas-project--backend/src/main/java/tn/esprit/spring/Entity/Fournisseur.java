package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Fournisseur  extends User{
	
	
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
