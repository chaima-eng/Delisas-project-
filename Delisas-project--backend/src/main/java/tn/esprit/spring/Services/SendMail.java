package tn.esprit.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.User;
import tn.esprit.spring.Repository.FournisseurRepository;
import tn.esprit.spring.Repository.UserRepository;
@Service
public class SendMail {
	@Autowired
	 EmailSenderService emailService;
	@Autowired
	FournisseurRepository frepo;
	
	public void traiterMail( int iduser) {
		// TODO Auto-generated method stub
		Fournisseur f = frepo.findById(iduser).orElse(null);
	emailService.sendSimpleEmail(f.getMail(), "creation du compte  ", "votre compte est cree avec un usename "+f.getMail()+
	"et un mot de passe "+f.getTel());
	
	
	}
}
