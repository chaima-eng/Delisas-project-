package tn.esprit.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Services.SendMail;

@RestController
public class MailController {
	@Autowired
    private SendMail email;
	
	@PutMapping("/traiter-mail/{iduser}")
	@ResponseBody
	public void traitermail(@PathVariable("iduser")int iduser){
		email.traiterMail(iduser);
	}
}
