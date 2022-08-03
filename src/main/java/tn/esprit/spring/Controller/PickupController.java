package tn.esprit.spring.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import tn.esprit.spring.Entity.Pickup;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Services.IPickupService;

@CrossOrigin(origins="*")
@RequestMapping("/pickup")
@RestController
public class PickupController {
	@Autowired
	IPickupService ps;
	
	
	@PostMapping("/ajouterEtaffecterpickupcolis/{idUser}/{idColis}")
	@ResponseBody
	public String ajouterEtaffecterpickupcolis(@RequestBody Pickup pickup, @PathVariable("idUser") int idUser, @RequestParam("idColis") List<Integer> idColis) {
		return ps.ajouterEtaffecterpickupcolis(pickup,idUser, idColis);
	}
	
	@GetMapping("/retrieve-Pickup")
	@ResponseBody
	List<Pickup> retrievePickup(){
	return ps.retrievePickup();
	}
	
	@GetMapping("/retrieve-Colis")
	@ResponseBody
	List<Colis> retrieveColis(){
	return ps.retrieveColis();
	}
	
	@GetMapping("/AfficherColis/{idPickup}")
	@ResponseBody
	 List<Colis> listedeColis(@PathVariable("idPickup") int idPickup) {
		return ps.listedeColis(idPickup);
	}
	
	

	
	
	
}
