package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket   implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idTicket;
	private String objet;
	private String descreption;
	 @Enumerated(EnumType.STRING)
	private  EtatTicket etatticket ;
	 
	@OneToOne
	@JsonIgnore
	private Colis colis;
	
	@ManyToOne
	@JsonIgnore
	private Fournisseur fournisseur;
	
	@ManyToOne
	@JsonIgnore
    private Personnel personnel;	
	 
	
}
