package com.example.backend.Service;

import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IntColisService {


     Colis save(Colis colis,int idhub,int idF);

      ResponseEntity<Colis> getColisyId(int Id)  throws ResourceNotFoundException;


    void export(HttpServletResponse response, int
            idcolis, String text, String filePath, int width, int height)
            throws DocumentException, IOException, WriterException;

   // ResponseEntity<Colis> updateColis(int id, Colis colis);
    Colis updateColis(Colis colis) throws ResourceNotFoundException;

    Map<String, Boolean> DeleteColis(int colisId)
             throws ResourceNotFoundException;


     List<Colis> getAllColis();


    void export(HttpServletResponse response,int idF, int idS,int
            idcolis,String chiffreCodeBar,int width, int height, String filePath) throws DocumentException, IOException,WriterException;














}
