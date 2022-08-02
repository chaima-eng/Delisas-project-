package com.example.backend.Repository;

import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntRolesRepo  extends JpaRepository<Roles,Integer> {

 Roles findByName(String Name);
    
}
