package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.Entity.ResponseTicket;


public interface ResponseRepository extends CrudRepository<ResponseTicket, Integer> {

}
