package com.example.backend.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.ws.Response;

import com.example.backend.Entity.Fournisseur;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Service.IntFournisseurSer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins="*")
@RequestMapping("/fournisseur")
@RestController
public class FournisseurController {
    @Autowired
    IntFournisseurSer Myservice;




/*


    @Autowired
    ServletContext context;

 */




    @PostMapping("/personnel")
    public ResponseEntity<Response>  addPersonnel (@RequestPart("file") MultipartFile file,
                                                   @RequestParam("fournisseur") String fournisseur) throws JsonParseException, JsonMappingException, Exception
    {

        return Myservice.addfournisseur(file, fournisseur);
    }



    @GetMapping("/get-Fournisseur/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") int Id)
            throws ResourceNotFoundException
    {
        return Myservice.getfournisseurById(Id);
    }

    @PutMapping("/update-Fournisseur/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable("id") int id, @RequestBody Fournisseur fourni)
    {
        return Myservice.updatefournisseur(id, fourni);
    }

    @DeleteMapping("/delete-Fournisseur/{id}")
    public Map<String, Boolean> deletePerso(@PathVariable(value = "id") int PersoId)
            throws ResourceNotFoundException

    {
        return Myservice.deletefournisseur(PersoId);
    }


    @GetMapping("/Fournisseur")
    List<Fournisseur> getAllFournisseur()
    {
        return Myservice.getAllfournisseur();
    }












}
