package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.Personnel;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Entity.Ticket;
import tn.esprit.spring.Repository.ColisRepository;
import tn.esprit.spring.Repository.FournisseurRepository;
import tn.esprit.spring.Repository.PersonnelRepository;
import tn.esprit.spring.Repository.TicketRepository;

@Service
public class TicketServiceImpl  implements ITicketService{
	@Autowired
	TicketRepository tr;
	@Autowired
	PersonnelRepository pr;
	@Autowired
	FournisseurRepository fr;
	@Autowired
	ColisRepository cr;
	@Override
	public Ticket addTicket(Ticket t) {
		// TODO Auto-generated method stub
		return tr.save(t);
	}

	@Override
	public void deleteticket(int id) {
		// TODO Auto-generated method stub
		tr.deleteById(id);
	}

	@Override
	public Ticket updateticket(Ticket t, int id) {
		// TODO Auto-generated method stub
		Ticket ticket = tr.findById(id).orElse(null);
		ticket.setDescreption(t.getDescreption());
		ticket.setObjet(t.getObjet());
		ticket.setEtatticket(t.getEtatticket());
			
			return tr.save(t);
	}

	@Override
	public List<Ticket> retrieveticket() {
		// TODO Auto-generated method stub
		return (List<Ticket>) tr.findAll();
	}

	

	
	
	
	

	@Override
	public Ticket addTicketToPersonnel(Ticket t, int idcolis, String username) {
		// TODO Auto-generated method stub
		Colis c =cr.findById(idcolis).orElse(null);
		Personnel p= pr.findByuserName((String)username);
		//Ticket ticket = tr.findById(id).orElse(null);
		t.setPersonnel(p);
		t.setColis(c);

		return tr.save(t);	}

	@Override
	public Ticket addTicketToFourni(Ticket t, int idcolis, String usename) {
		// TODO Auto-generated method stub
		Colis c =cr.findById(idcolis).orElse(null);
		Fournisseur f= fr.findByuserName(usename);
		t.setFournisseur(f);
		t.setColis(c);

		return tr.save(t);	}
	
}
