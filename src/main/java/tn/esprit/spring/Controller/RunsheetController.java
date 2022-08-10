package tn.esprit.spring.Controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Personnel;
import tn.esprit.spring.Entity.Pickup;
import tn.esprit.spring.Entity.Runsheet;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Services.IPickupService;
import tn.esprit.spring.Services.IRunsheetService;

@CrossOrigin(origins="*")
@RequestMapping("/runsheet")
@RestController
public class RunsheetController {
	@Autowired
	IRunsheetService rs;
	
	
	@PostMapping("/ajouterEtaffectercRunsheet/{codeabar}/{idUser}")
	@ResponseBody
	public String ajouterEtaffectercRunsheet(@RequestBody Runsheet r, @RequestParam("code_a_bar") List<String>  codeabar, @PathVariable("idUser") int idUser) {
		return rs.ajouterEtaffectercRunsheet(r, codeabar, idUser);
	}
	
	@PutMapping("/updateRunsheet/{idRunsheet}")
	@ResponseBody
	void updateRunsheet(@RequestBody Runsheet r,@PathVariable("idRunsheet") int idRunsheet){
		 rs.updateRunsheet(r,idRunsheet);
	}

	@GetMapping("/retrieve-Runsheet")
	@ResponseBody
	List<Runsheet> retrieveSociete(){
	return rs.retrieveRunsheet();
	}
	
	@DeleteMapping("/delete-Runsheet/{idRunsheet}")
	void deleteSociete(@PathVariable(name="idRunsheet")int idRunsheet){
		rs.deleteRunsheet(idRunsheet);
	}
	
	@GetMapping("/retrieveColis/{codeabar}")
	@ResponseBody
	 Colis retrieveColis(@PathVariable(name="codeabar")String codeabar) {
		return rs.retrieveColis(codeabar);
	}
	
	@GetMapping("/getnbrColis/{idRunsheet}")
	@ResponseBody
	 int getnbrColis(@PathVariable(name="idRunsheet")int idRunsheet) {
		return rs.getnbrColis(idRunsheet);
	}
	
	@GetMapping("/getPersonnel/{username}")
	@ResponseBody
	 Personnel getPersonnel(@PathVariable(name="username")String username) {
		return rs.getPersonnel(username);
	}
}
