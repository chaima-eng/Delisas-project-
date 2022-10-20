package com.example.backend.Repository;


import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntUserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
    User findByEmail(String email);


}
