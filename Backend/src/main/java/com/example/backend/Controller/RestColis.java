package com.example.backend.Controller;

import antlr.CodeGenerator;
import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Personnel;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Service.IntColisService;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.MacUkraine;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Colis")
@Slf4j
public class RestColis {

    @Autowired
    IntColisService Myservice;
 //   private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode/QrCode.png";


    @PostMapping("/save")
    public Colis save(@RequestBody Colis colis)
    {
        return Myservice.save(colis);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Colis> getColisByID(@PathVariable(value = "id") int Id)
            throws ResourceNotFoundException
    {
        return Myservice.getColisyId(Id);
    }

    //to check !!
    @PutMapping("/updateColis/{id}")
    public ResponseEntity<Colis> updateColis(@PathVariable("id") int id, @RequestBody Colis colis)
    {
        return Myservice.updateColis(id,colis);
    }


    @DeleteMapping("/DeleteColis/{id}")
    public Map<String, Boolean> deleteColis(@PathVariable(value = "id") int ColisId)
            throws ResourceNotFoundException

    {
        return Myservice.DeleteColis(ColisId);
    }


    @GetMapping("/AllColis")
    List<Colis> getAll()
    {
        return Myservice.getAllColis();
    }

/*
    @GetMapping("/colis/export/pdf")
    public void exportToPDF(HttpServletResponse response,int idcolis) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ticket" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Myservice.export(response,idcolis);

    }



 */













    /*
    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}")
    public void download(
            @PathVariable("codeText") String codeText
           )
            throws Exception {
        Myservice.generateQRCodeImage(codeText,  QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/genrateQRCode/{codeText}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("codeText") String codeText)
            throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(Myservice.getQRCodeImage(codeText));
    }


     */














}
