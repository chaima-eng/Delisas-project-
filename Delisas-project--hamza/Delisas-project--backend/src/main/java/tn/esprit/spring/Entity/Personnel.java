package tn.esprit.spring.Entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Personnel extends User {

    private String matriculevehicule;
    private String permis;
    private String cartegrise;
    private String photo;


   /* @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();

*/

    @Enumerated(EnumType.STRING)
    private Role role;
    private Float salaire;

    
    @OneToMany
	@JsonIgnore
	private Set<Ticket> tickets;
}
