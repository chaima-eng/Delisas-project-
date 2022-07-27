package com.example.backend.Service;


import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntFournisseurRepo;
import com.example.backend.Repository.IntPersonnelRepo;
import com.example.backend.Repository.IntRolesRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.xml.ws.Response;
import java.io.File;
import java.util.*;

@Service
@Slf4j
public class FournisseurService implements IntFournisseurSer {

    @Autowired
    IntFournisseurRepo frepo;

    @Autowired
    ServletContext context;

    @Autowired
    PasswordEncoder passwordEncoder;





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

        perso.setPassword(passwordEncoder.encode(DefaultPasword));
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
    public ResponseEntity<Fournisseur> updateFournisseur(int id, Fournisseur fourni) {
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

    @Override
    public ResponseEntity<Fournisseur> updatefournisseur(int id, Fournisseur fourni) {
        // TODO Auto-generated method stub
        return null;
    }












}
