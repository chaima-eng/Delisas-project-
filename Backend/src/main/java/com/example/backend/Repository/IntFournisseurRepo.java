package com.example.backend.Repository;

import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntFournisseurRepo  extends JpaRepository<Fournisseur,Integer> {
}
