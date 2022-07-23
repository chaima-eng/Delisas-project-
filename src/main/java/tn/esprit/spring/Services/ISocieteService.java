package tn.esprit.spring.Services;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.Entity.Societe;

public interface ISocieteService {



	void deleteSociete(int id);

	

	List<Societe> retrieveSociete();

	ResponseEntity<Response> add(MultipartFile file, String Societe)
			throws JsonParseException, JsonMappingException, Exception;

	ResponseEntity<Societe> updatesociete(int id, Societe s);

	//ResponseEntity<Response> add(MultipartFile file, String Societe)
		//	throws JsonParseException, JsonMappingException, Exception;

}
