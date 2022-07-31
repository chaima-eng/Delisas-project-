package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.Entity.Ticket;
import tn.esprit.spring.Entity.User;

public interface TicketRepository  extends CrudRepository<Ticket, Integer>{

}
