package com.example.backend.Service;

import com.example.backend.Entity.Personnel;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntPersonnelRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class PersonnelService implements IntPersonnelService
{
    @Autowired
    private IntPersonnelRepo MyPersonnelRepo;
    @Autowired
    ServletContext context;


    @Override
    public ResponseEntity<Response> addPersonnel(MultipartFile file, String personnel) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Ok .............");
        Personnel perso = new ObjectMapper().readValue(personnel, Personnel.class);
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



        perso.setPhoto(newFileName);

        Personnel art = MyPersonnelRepo.save(perso);



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
    public ResponseEntity<Personnel> getPersoById( int Id)
            throws ResourceNotFoundException {
        Personnel personnel = MyPersonnelRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("personnel not found for this id :: " + Id));
        return ResponseEntity.ok().body(personnel);
    }


   @Override
    public ResponseEntity<Personnel> updatePerso(int id, Personnel personnel) {
        System.out.println("Update Personnel with ID = " + id + "...");
        Optional<Personnel> Personnelinfo = MyPersonnelRepo.findById(id);
        if (Personnelinfo.isPresent()) {
            Personnel perso = Personnelinfo.get();
            perso.setCartegrise(personnel.getCartegrise());
            perso.setMatriculevehicule(personnel.getMatriculevehicule());
            perso.setPermis(personnel.getPermis());
            perso.setMontant_l(personnel.getMontant_l());
            perso.setSalaire(personnel.getSalaire());
            perso.setRole(personnel.getRole());

            return new ResponseEntity<>(MyPersonnelRepo.save(personnel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Map<String, Boolean> deletePerso(int PersoId) throws ResourceNotFoundException {
        Personnel Personnel = MyPersonnelRepo.findById(PersoId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found  id :: " + PersoId));
        MyPersonnelRepo.delete(Personnel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }









}
