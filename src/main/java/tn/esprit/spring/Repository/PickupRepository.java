package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entity.Pickup;

public interface PickupRepository extends JpaRepository<Pickup,Integer>{
	//@Query("SELECT COUNT(DISTINCT to_user_id) FROM Complaints c ")  
    //public int SommeComplaint();
	
	
}
