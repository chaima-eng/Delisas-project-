package com.example.backend.Service;

import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;

import java.util.List;
import java.util.Map;

public interface IntFournisseurSer {

    Map<String, Boolean> deletefournisseur(int PersoId) throws ResourceNotFoundException;

    List<Fournisseur> getAllfournisseur();

    ResponseEntity<Fournisseur> updatefournisseur(int id, Fournisseur fourni);

    ResponseEntity<Response> addfournisseur(MultipartFile file, String fournisseur)
            throws JsonParseException, JsonMappingException, Exception;

    ResponseEntity<Fournisseur> getfournisseurById(int Id) throws ResourceNotFoundException;

    ResponseEntity<Fournisseur> updateFournisseur(int id, Fournisseur fourni);




}
