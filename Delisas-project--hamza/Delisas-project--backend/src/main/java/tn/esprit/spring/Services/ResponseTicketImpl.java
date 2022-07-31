package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.ResponseTicket;
import tn.esprit.spring.Entity.Ticket;
import tn.esprit.spring.Repository.ResponseRepository;
import tn.esprit.spring.Repository.TicketRepository;
@Service
public class ResponseTicketImpl implements IResponseTicket {
	@Autowired
	ResponseRepository respo;
	@Autowired
	TicketRepository tr;
	@Override
	public ResponseTicket addResponse(ResponseTicket r) {
		// TODO Auto-generated method stub
		return respo.save(r);
	}

	@Override
	public void deleteResponse(int id) {
		// TODO Auto-generated method stub
		respo.deleteById(id);
	}

	@Override
	public ResponseTicket updateResponse(ResponseTicket r, int id) {
		// TODO Auto-generated method stub
		ResponseTicket rep = respo.findById(id).orElse(null);
		rep.setText(r.getText());
			
			return respo.save(r);	
	
			}

	@Override
	public List<ResponseTicket> retrieveResponse() {
		// TODO Auto-generated method stub
		return (List<ResponseTicket>) respo.findAll();
	}

	@Override
	public ResponseTicket addResponseToTicket(ResponseTicket r, int id) {
		// TODO Auto-generated method stub
		Ticket t = tr.findById(id).orElse(null);
		
		ResponseTicket rep = respo.findById(id).orElse(null);
		rep.setTicket(t);
			
			return respo.save(r);	
	
	}

}
