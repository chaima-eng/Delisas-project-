package tn.esprit.spring.Controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Entity.Gouvernerat;
import tn.esprit.spring.Entity.Hub;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Services.IHubService;

@CrossOrigin(origins="*")
@RequestMapping("/Hub")
@RestController
public class HubController {
	@Autowired
	IHubService hs;

	@PostMapping("/add")
	@ResponseBody
	public Hub  addHub(@RequestBody Hub h) {
		return hs.addhub(h);
	}
	
	@GetMapping("/retrieve-Hub")
	@ResponseBody
	List<Hub> retrieveHub(){
	return hs.retrieveHub();
	}
	
	/*@GetMapping("/AfficherComplaintStatus/{gouvernerat}")
	@ResponseBody
	 List<Hub> AfficherGouverneratParHub(@PathVariable("gouvernerat")Gouvernerat gouvernerat) {
		return hs.listeDeGouverneratParHub(gouvernerat);
	}*/
	
	@DeleteMapping("/delete-Hub/{id_Hub}")
	void deleteHub(@PathVariable(name="id_Hub")int id_Hub){
		hs.deleteHub(id_Hub);
	}
	
	@PutMapping("/update-Hub/{id_Hub}")
	@ResponseBody
	Hub updateHub(@RequestBody Hub a,@PathVariable("id_Hub") int id_Hub){
		return hs.updateHub(a, id_Hub);
	}
}
