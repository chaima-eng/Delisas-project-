package com.example.backend.Controller;

import antlr.CodeGenerator;
import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Personnel;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntColisRepo;
import com.example.backend.Service.ColisService;
import com.example.backend.Service.IntColisService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.zxing.WriterException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.cs.ext.MacUkraine;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode/QrCode.png";
    ColisService Service;

    @Autowired
    private IntColisRepo MyColisRepo;


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


    @GetMapping("/colis/export/pdf")
    public void exportToPDF(HttpServletResponse response,int idcolis,int idS,int idF) throws DocumentException, IOException, WriterException {

        Colis colis=MyColisRepo.getById(idcolis);
        String chiffreCodeBar = colis.getCode_a_bar();

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ticket" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Myservice.export(response, idF,idS,idcolis,chiffreCodeBar,70,70,QR_CODE_IMAGE_PATH);

    }





    @GetMapping(value = "/genrateAndDownloadQRCode/{text}/{width}/{height}")
    public void download(
            @PathVariable("text") String text,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        Service.generateQRCodeImage(text,width, height, QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/genrateQRCode/{text}/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("text") String  text,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(Service.getQRCodeImage(text,width, height));
    }



    @RequestMapping(value = "/barcode/{id}", method = RequestMethod.GET)
    public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception
    {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(Myservice.getBarCodeImage(id, 200, 50));
        outputStream.flush();
        outputStream.close();
    }







    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Response>  addSociete(@RequestPart("file") MultipartFile file, @RequestParam("societe") String s)throws JsonParseException, JsonMappingException, Exception {
        return Myservice.addSociete(file,s);
    }














}
