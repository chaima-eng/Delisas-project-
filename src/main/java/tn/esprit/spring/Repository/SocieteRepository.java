package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entity.Societe;

@Repository
public interface SocieteRepository extends CrudRepository<Societe, Integer>{

}
