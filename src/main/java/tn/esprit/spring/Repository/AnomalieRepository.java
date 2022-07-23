package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Entity.Societe;

public interface AnomalieRepository extends CrudRepository<Anomalie_colis, Integer>{


}
