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

import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Services.ISocieteService;

@CrossOrigin(origins="*")
@RequestMapping("/societe")
@RestController
public class SocieteController {
	//http://localhost:8089/SpringMVC/swagger-ui/index.html
	@Autowired
	ISocieteService societeService; 

	@PostMapping("/addSociete")
	@ResponseBody
	public Societe  addSociete(@RequestBody Societe s) {
		 return societeService.addSociete(s);
	}
	
	@DeleteMapping("/delete-Societe/{id}")
	void deleteSociete(@PathVariable(name="id")int id){
		societeService.deleteSociete(id);
	}
	
	@PutMapping("/update-Societe/{id}")
	@ResponseBody
	Societe updateSociete(@RequestBody Societe s,@PathVariable("id") int id){
		return societeService.updateSociete(s,id);
	}
	

	@GetMapping("/retrieve-Societe")
	@ResponseBody
	List<Societe> retrieveSociete(){
	return societeService.retrieveSociete();
	}
}
