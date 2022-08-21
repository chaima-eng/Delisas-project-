package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Fournisseur;

public interface ColisRepository extends JpaRepository<Colis, Integer> {

}
