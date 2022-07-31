package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Entity.Ticket;

public interface ITicketService {

	Ticket addTicket(Ticket t);
	Ticket addTicketToFourni(Ticket t , int idcolis,String usename);
	Ticket addTicketToPersonnel(Ticket t,int idcolis,String username);

	void deleteticket(int id);

	Ticket updateticket(Ticket t, int id);

	List<Ticket> retrieveticket();
	}
