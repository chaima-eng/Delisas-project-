package tn.esprit.spring.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.exception.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
public interface IFournisseurService {
	 //void LoginAdmin(String password,String username);

	 //Fournisseur loadUserByUserName(String UserName);

	// void LoginPersonnel(String password, String username);


	Map<String, Boolean> deletefournisseur(int PersoId) throws ResourceNotFoundException;

	List<Fournisseur> getAllfournisseur();

	ResponseEntity<Fournisseur> updatefournisseur(int id, Fournisseur fourni);

	ResponseEntity<Response> addfournisseur(MultipartFile file, String fournisseur)
			throws JsonParseException, JsonMappingException, Exception;

	ResponseEntity<Fournisseur> getfournisseurById(int Id) throws ResourceNotFoundException;

	ResponseEntity<Fournisseur> updateFournisseur(int id, Fournisseur fourni);

}
