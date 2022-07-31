package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Repository.FournisseurRepository;
import tn.esprit.spring.exception.ResourceNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletContext;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class FournisseurServiceImpl  implements IFournisseurService{

	@Autowired
	FournisseurRepository frepo;

	@Autowired
    ServletContext context;
/*
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 @Override
    public void LoginAdmin(String password, String username) {
    	Fournisseur perso = new Fournisseur();
        if(username.equals("admin")&&password.equals("admin")) {
            System.out.println("Welcome");
            perso.setPassword(password);
            perso.setUserName(username);
            frepo.save(perso);
        }

       else
            System.out.println("verify your credentials");
      

    }

    @Override
    public Fournisseur loadUserByUserName(String userName)
    {

        return frepo.findByuserName(userName);
    }

*/

    @Override
    public ResponseEntity<Response> addfournisseur(MultipartFile file, String fournisseur) throws JsonParseException, JsonMappingException, Exception
    {

        System.out.println("Ok .............");
        Fournisseur perso = new ObjectMapper().readValue(fournisseur, Fournisseur.class);
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



      //   String pass = perso.getPassword();
         String DefaultPasword = perso.getTel();

        //perso.setPassword(passwordEncoder.encode(DefaultPasword));
        perso.setImageSociete(newFileName);

        Fournisseur art = frepo.save(perso);



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
    public ResponseEntity<Fournisseur> getfournisseurById( int Id)
            throws ResourceNotFoundException {
    	Fournisseur personnel = frepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("personnel not found for this id :: " + Id));
        return ResponseEntity.ok().body(personnel);
    }


   

    @Override
    public Map<String, Boolean> deletefournisseur(int PersoId) throws ResourceNotFoundException {
    	Fournisseur Personnel = frepo.findById(PersoId)
                .orElseThrow(() -> new ResourceNotFoundException(" not found   :: " + PersoId));
    	frepo.delete(Personnel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

	@Override
	public List<Fournisseur> getAllfournisseur() {
		// TODO Auto-generated method stub
        return (List<Fournisseur>) frepo.findAll();
	}

	

	@Override
	public ResponseEntity<Fournisseur> updatefournisseur(int id, Fournisseur fourni) {
		// TODO Auto-generated method stub
		System.out.println("Update Personnel with ID = " + id + "...");
        Optional<Fournisseur> Personnelinfo = frepo.findById(id);
        if (Personnelinfo.isPresent()) {
        	Fournisseur perso = Personnelinfo.get();
            perso.setAdresseSociete(fourni.getAdresseSociete());
            perso.setDateDebutContrat(fourni.getDateDebutContrat());
            perso.setDateDebutFin(fourni.getDateDebutFin());
            perso.setGouverneratLivraison(fourni.getGouverneratLivraison());
            perso.setGouverneratSociete(fourni.getGouverneratSociete());
            perso.setPrixLivrasion(fourni.getPrixLivrasion());

            return new ResponseEntity<>(frepo.save(fourni), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	

	



 

 /*
    @Override
    public void LoginPersonnel(String password, String username) {
    	frepo.findAll().forEach(P -> {
            if (P.getPassword().equals(password) && P.getUserName().equals(username))
                System.out.println("done");
            else
                System.out.println("erreur");
        } );
    }
   */
}