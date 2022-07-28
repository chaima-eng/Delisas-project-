package tn.esprit.spring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Entity.Fournisseur;

public interface IntFournisseurRepo  extends JpaRepository<Fournisseur,Integer> {
}
