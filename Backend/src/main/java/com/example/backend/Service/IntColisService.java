package com.example.backend.Service;

import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IntColisService {


    List<Colis> getColisCrée();

    List<Colis> getColisEn_stock();

    List<Colis> getColisEn_cours_livraison();

    List<Colis> Planification_retour();


    List<Colis> Retourné();

    List<Colis> Livrée_payée();


    List<Colis> En_cours_Enlevement();




    List<Colis> ListColisByFournisseur(int idFournisseur);










    void save(Colis colis,int idhub,int idF);



     ResponseEntity<Colis> getColisyId(int Id)  throws ResourceNotFoundException;



    void export(HttpServletResponse response, int
            idcolis, String text, String filePath, int width, int height)
            throws DocumentException, IOException, WriterException;

 
    Colis updateColis(Colis colis) throws ResourceNotFoundException;

    Map<String, Boolean> DeleteColis(int colisId)
             throws ResourceNotFoundException;


     List<Colis> getAllColis();


    void export(HttpServletResponse response,int idF, int idS,int
            idcolis,String chiffreCodeBar,int width, int height, String filePath) throws DocumentException, IOException,WriterException;



     ResponseEntity<Colis> updateColisWithId(int id, Colis colis);


     String GenerateChiffreCodeBar2(int idColis);









}
