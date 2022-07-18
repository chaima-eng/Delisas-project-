package com.example.backend.Service;

import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IntColisService {


     Colis save(Colis colis);

      ResponseEntity<Colis> getColisyId(int Id)  throws ResourceNotFoundException;




    ResponseEntity<Colis> updateColis(int id, Colis colis);


     Map<String, Boolean> DeleteColis(int colisId)
             throws ResourceNotFoundException;


     List<Colis> getAllColis();
     /*
     void generateQRCodeImage(String text,  String filePath)
            throws WriterException, IOException;
      byte[] getQRCodeImage(String text) throws WriterException, IOException ;

      */

    void export(HttpServletResponse response,int
            idcolis,String text, String filePath) throws DocumentException, IOException,WriterException;
















}
