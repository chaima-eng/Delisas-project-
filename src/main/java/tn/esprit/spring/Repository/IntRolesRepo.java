package tn.esprit.spring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Entity.Roles;

public interface IntRolesRepo  extends JpaRepository<Roles,Integer> {

 Roles findByName(String Name);
    
}
