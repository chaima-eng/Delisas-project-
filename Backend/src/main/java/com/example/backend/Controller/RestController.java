package com.example.backend.Controller;

import com.example.backend.Entity.Personnel;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntPersonnelRepo;
import com.example.backend.Service.IntPersonnelService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.xml.ws.Response;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Rest")
public class RestController {


    @Autowired
    IntPersonnelService Myservice;

    @Autowired
    private IntPersonnelRepo MyPersonnelRepo;
    @Autowired
    ServletContext context;

    @PostMapping("/personnel")
    public ResponseEntity<Response>  addPersonnel (@RequestPart("file") MultipartFile file,
                                                   @RequestParam("personnel") String personnel) throws JsonParseException, JsonMappingException, Exception
    {

        return Myservice.addPersonnel(file,personnel);
    }


    @GetMapping("/personnel/{id}")
    public ResponseEntity<Personnel> getPersoById(@PathVariable(value = "id") int Id)
            throws ResourceNotFoundException
    {
        return Myservice.getPersoById(Id);
    }

    @PutMapping("/perso/{id}")
    public ResponseEntity<Personnel> updatePerso(@PathVariable("id") int id, @RequestBody Personnel personnel)
    {
        return Myservice.updatePerso(id,personnel);
    }

    @DeleteMapping("/Perso/{id}")
    public Map<String, Boolean> deletePerso(@PathVariable(value = "id") int PersoId)
            throws ResourceNotFoundException

    {
        return Myservice.deletePerso(PersoId);
    }


    @GetMapping("/personnel")
    List<Personnel> getAllPersonnel()
    {
        return Myservice.getAllPersonnel();
    }






}
