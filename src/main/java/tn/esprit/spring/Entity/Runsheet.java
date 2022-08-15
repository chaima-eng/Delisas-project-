package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
  @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Runsheet implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idRunsheet;
	private LocalDate datecreation= LocalDate.now();
	private  Float prixtotal;
	private String codeabarre;

	 @Enumerated(EnumType.STRING)
	    private Etat_debrief etat_debrief ;
	 
	// @JsonIgnore
	   @OneToOne
	   private Personnel personnel;
	 @JsonIgnore
	 @ManyToMany(mappedBy="runsheets",cascade = CascadeType.ALL)
	 private Set<Colis> coliss;
}
