package com.example.backend.Service;


import com.example.backend.Entity.Personnel;
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


  //  void LoginPersonnel(String password,String username );

    void LoginAdmin(String password,String username);

     Personnel loadUserByUserName(String UserName);

    ResponseEntity<Response>  addPersonnel ( MultipartFile file,
                                          String personnel)
            throws JsonParseException, JsonMappingException, Exception;



    ResponseEntity<Personnel> getPersoById(int Id) throws ResourceNotFoundException;


    ResponseEntity<Personnel> updatePerso(int id,Personnel personnel);


     Map<String, Boolean> deletePerso( int PersoId)
            throws ResourceNotFoundException;


    List<Personnel> getAllPersonnel();





}
