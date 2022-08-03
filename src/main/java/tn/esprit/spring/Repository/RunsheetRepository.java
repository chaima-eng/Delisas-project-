package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entity.Runsheet;
@Repository
public interface RunsheetRepository extends JpaRepository<Runsheet,Integer>{
	
	
}
