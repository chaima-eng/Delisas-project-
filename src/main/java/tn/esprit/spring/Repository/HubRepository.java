package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.Entity.Gouvernerat;
import tn.esprit.spring.Entity.Hub;
import tn.esprit.spring.Entity.Societe;

public interface HubRepository extends JpaRepository<Hub, Integer>{
	
}
