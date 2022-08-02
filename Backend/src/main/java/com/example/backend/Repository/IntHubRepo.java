package com.example.backend.Repository;

import com.example.backend.Entity.Hub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntHubRepo extends CrudRepository<Hub,Integer> {
}
