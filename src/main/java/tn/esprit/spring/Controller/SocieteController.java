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

import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Services.ISocieteService;

@CrossOrigin(origins="*")
@RequestMapping("/societe")
@RestController
public class SocieteController {
	//http://localhost:8089/SpringMVC/swagger-ui/index.html
	@Autowired
	ISocieteService societeService; 

	
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Response>  addSociete(@RequestPart("file") MultipartFile file,@RequestParam("societe") String s)throws JsonParseException, JsonMappingException, Exception {
		 return societeService.add(file,s);
	}
	
	
	@DeleteMapping("/delete-Societe/{id}")
	void deleteSociete(@PathVariable(name="id")int id){
		societeService.deleteSociete(id);
	}
	
	@PutMapping("/update-Societe/{id}")
	@ResponseBody
	ResponseEntity<Societe> updateSociete(@PathVariable("id") int id,@RequestBody Societe s){
		return societeService.updatesociete(id,s);
	}
	

	@GetMapping("/retrieve-Societe")
	@ResponseBody
	List<Societe> retrieveSociete(){
	return societeService.retrieveSociete();
	}
}
