package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.ResponseTicket;
import tn.esprit.spring.Entity.Ticket;

public interface IResponseTicket {

	ResponseTicket addResponse (ResponseTicket r);
	ResponseTicket addResponseToTicket (ResponseTicket r , int id );

	void deleteResponse(int id);

	ResponseTicket updateResponse(ResponseTicket r, int id);

	List<ResponseTicket> retrieveResponse();
	
}
