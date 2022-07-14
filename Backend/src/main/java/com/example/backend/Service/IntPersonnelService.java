package com.example.backend.Service;


import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IntPersonnelService {


  Personnel getPersonnel(String username);


  //  void LoginPersonnel(String password,String username );

   void addRoleToUser(String username, String rolename);
  //  void LoginAdmin(String password,String username);


    ResponseEntity<Response>  addPersonnel ( MultipartFile file,
                                          String personnel,int idroles)
            throws JsonParseException, JsonMappingException, Exception;


    public Roles save(Roles role);

    ResponseEntity<Personnel> getPersoById(int Id) throws ResourceNotFoundException;


    ResponseEntity<Personnel> updatePerso(int id,Personnel personnel);


     Map<String, Boolean> deletePerso( int PersoId)
            throws ResourceNotFoundException;


    List<Personnel> getAllPersonnel();





}
