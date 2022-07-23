package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.Entity.Societe;


public interface SocieteRepository extends CrudRepository<Societe, Integer>{

}
