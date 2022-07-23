package tn.esprit.spring.Services;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;

import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Repository.SocieteRepository;
import javax.servlet.ServletContext;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class SocieteService implements ISocieteService{
	@Autowired
	SocieteRepository societeRepository;
	@Autowired
    ServletContext context;
	

	
	@Override
	public void deleteSociete(int id) {
		societeRepository.deleteById(id);
	}
	
	
	
	@Override
	public List<Societe> retrieveSociete() {
		return (List<Societe>) societeRepository.findAll();
	}
	
	@Override
    public ResponseEntity<Response> add(MultipartFile file, String Societe) throws JsonParseException, JsonMappingException, Exception
    {

        System.out.println("Ok .............");
        Societe s = new ObjectMapper().readValue(Societe, Societe.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mkdir success.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }



     
        s.setLogo(newFileName);

        Societe art = societeRepository.save(s);



        if (art != null)
        {
            return new ResponseEntity<Response>( HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }



    }
	

	   @Override
	    public ResponseEntity<Societe> updatesociete(int id, Societe s) {
	        System.out.println("Update Personnel with ID = " + id + "...");
	        Optional<Societe> so = societeRepository.findById(id);
	        if (so.isPresent()) {
	        	Societe societe = so.get();
	        	societe.setLogo(s.getLogo());
	    		societe.setNom(s.getNom());
	    		societe.setTel(s.getTel());
	    		societe.setAdresse(s.getAdresse());
	    		societe.setAdresse_Mail(s.getAdresse_Mail());
	    		societe.setMatricule_Fiscale(s.getMatricule_Fiscale());

	            return new ResponseEntity<>(societeRepository.save(societe), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
