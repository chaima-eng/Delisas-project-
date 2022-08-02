package com.example.backend.Repository;

import com.example.backend.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntPersonnelRepo extends JpaRepository<Personnel,Integer>
{

    Personnel findByUserName(String userName);
    Personnel findByEmail(String email);



}
