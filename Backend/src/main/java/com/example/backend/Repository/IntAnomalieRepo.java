package com.example.backend.Repository;

import com.example.backend.Entity.AnomalieColis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntAnomalieRepo  extends JpaRepository<AnomalieColis,Integer> {
}
