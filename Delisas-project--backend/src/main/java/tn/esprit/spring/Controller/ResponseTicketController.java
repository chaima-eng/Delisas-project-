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

import tn.esprit.spring.Entity.ResponseTicket;
import tn.esprit.spring.Entity.Ticket;
import tn.esprit.spring.Services.IResponseTicket;
import tn.esprit.spring.Services.ITicketService;

@CrossOrigin(origins="*")
@RequestMapping("/Ticket")
@RestController
public class ResponseTicketController {
	
	@Autowired
	IResponseTicket rs; 

	@PostMapping("/addResponse")
	@ResponseBody
	public ResponseTicket  addResponse(@RequestBody ResponseTicket r) {
		 return rs.addResponse(r);
	}
	@PostMapping("/addResponseToTicket/{id}")
	@ResponseBody
	public ResponseTicket  addResponseToTicket(@RequestBody ResponseTicket r,@PathVariable ("id")int id)	{
		 return rs.addResponseToTicket(r, id);
	}
	
	@DeleteMapping("/deleteResponse/{id}")
	void deleteResponse(@PathVariable(name="id")int id){
		rs.deleteResponse(id);
	}
	
	@PutMapping("/updateResponse/{id}")
	@ResponseBody
	ResponseTicket updateResponse(@RequestBody ResponseTicket r,@PathVariable("id") int id){
		return rs.updateResponse(r, id);
	}
	

	@GetMapping("/retrieveResponse")
	@ResponseBody
	List<ResponseTicket> retrieveResponse(){
	return rs.retrieveResponse();
	}
}
