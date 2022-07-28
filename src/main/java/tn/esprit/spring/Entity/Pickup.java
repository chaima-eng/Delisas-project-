package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Pickup implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idPickup;
	private LocalDate date_creation= LocalDate.now();;
	private  Float prix_total;/*=(float)00.0;*/
	   @JsonIgnore
	   @ManyToMany(mappedBy="pickups",cascade = CascadeType.ALL)
	   private Set<Colis> Coliss;
	   
	   @JsonIgnore
	   @OneToOne
	   private Personnel personnel;

	

	

	
}
