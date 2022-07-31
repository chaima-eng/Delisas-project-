package tn.esprit.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entity.Ticket;
import tn.esprit.spring.Services.ITicketService;

@CrossOrigin(origins="*")
@RequestMapping("/Ticket")
@RestController
public class TicketController {

	@Autowired
	ITicketService ts; 

	@PostMapping("/addticket")
	@ResponseBody
	public Ticket  addSociete(@RequestBody Ticket t) {
		 return ts.addTicket(t);
	}
	@PostMapping("/addTicketToFourni/{idcolis}/{username}")
	@ResponseBody
	Ticket addTicketToFourni(@RequestBody Ticket t,@PathVariable("idcolis")int idcolis,@PathVariable("username")String username){//@request taayet lel les attributs
	return ts.addTicketToFourni(t, idcolis, username);
	}
	@PostMapping("/addTicketToPersonnel/{idcolis}/{username}")
	@ResponseBody
	Ticket addTicketToPersonnel(@RequestBody Ticket t,@PathVariable("idcolis")int idcolis,@PathVariable("username")String username){//@request taayet lel les attributs
	return ts.addTicketToPersonnel(t, idcolis,  username);
	}
	
	@DeleteMapping("/delete-ticket/{id}")
	void deleteSociete(@PathVariable(name="id")int id){
		ts.deleteticket(id);
	}
	
	@PutMapping("/update-ticket/{id}")
	@ResponseBody
	Ticket updateSociete(@RequestBody Ticket t,@PathVariable("id") int id){
		return ts.updateticket(t, id);
	}
	

	@GetMapping("/retrieve-ticket")
	@ResponseBody
	List<Ticket> retrieveSociete(){
	return ts.retrieveticket();
	}
}
