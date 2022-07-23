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
import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Services.IAnomalieService;

@CrossOrigin(origins="*")
@RequestMapping("/anomalie")
@RestController
public class AnomalieController {
	
	@Autowired
	IAnomalieService anomalieService; 

	@PostMapping("/addAnomalie")
	@ResponseBody
	public Anomalie_colis addAnomalie(@RequestBody Anomalie_colis a){
		 return anomalieService.addAnomalie(a);
	}
	
	
	@DeleteMapping("/delete-Anomalie/{id_Anomalie}")
	void deleteAnomalie(@PathVariable(name="id_Anomalie")int id_Anomalie){
		anomalieService.deleteAnomalie(id_Anomalie);
	}
	
	@PutMapping("/update-Anomalie/{id_Anomalie}")
	@ResponseBody
	Anomalie_colis updateAnomalie(@RequestBody Anomalie_colis a,@PathVariable("id_Anomalie") int id_Anomalie){
		return anomalieService.updateAnomalie(a, id_Anomalie);
	}
	

	@GetMapping("/retrieve-Anomalie")
	@ResponseBody
	List<Anomalie_colis> retrieveAnomalie(){
	return anomalieService.retrieveAnomalie();
	}
}
