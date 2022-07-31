package com.example.backend.Repository;

import com.example.backend.Entity.Fournisseur;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntFournisseurRepo  extends JpaRepository<Fournisseur,Integer> {




}
