package com.example.backend.Repository;

import com.example.backend.Entity.Colis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntColisRepo  extends JpaRepository<Colis,Integer> {

}
