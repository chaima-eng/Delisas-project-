package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class ResponseTicket  implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idresponse;
	private String text ;
	
	
	@ManyToOne
	@JsonIgnore
	private Ticket ticket;
}
