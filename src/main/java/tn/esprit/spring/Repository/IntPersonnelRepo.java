package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entity.Personnel;

@Repository
public interface IntPersonnelRepo extends JpaRepository<Personnel,Integer>
{

    Personnel findByUserName(String userName);
    Personnel findByEmail(String email);
   


}
