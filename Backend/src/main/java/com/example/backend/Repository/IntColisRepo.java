package com.example.backend.Repository;

import com.example.backend.Entity.Colis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntColisRepo  extends JpaRepository<Colis,Integer> {


  //  Colis findByCode_a_bar(String code_a_bar);





}
