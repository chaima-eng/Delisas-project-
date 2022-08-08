package tn.esprit.spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fournisseur extends User {
    private String nomsociete;
    private String adresse_societe;
    private String gouvernerat;

    /*
    @ManyToMany
    @JsonIgnore
    private Set<Colis> colis;

     */


}
