package com.example.backend.Controller;

import antlr.CodeGenerator;
import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Etat_colis;
import com.example.backend.Entity.Fournisseur;
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

import javax.servlet.ServletOutputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Colis")
@Slf4j
@CrossOrigin("*")
public class RestColis {




    @Autowired
    IntColisService Myservice;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode/QrCode.png";
    ColisService Service;

    @Autowired
    private IntColisRepo MyColisRepo;







    @PostMapping("/save/{idColis}")
    public String GenerateChiffreCodeBar2(@PathVariable("idColis") int idColis)
    {
        return Myservice.GenerateChiffreCodeBar2(idColis);
    }


    @PostMapping("/save/{idhub}/{idF}")
    public void save(@RequestBody Colis colis,@PathVariable("idhub") int idhub,@PathVariable("idF") int idF)
    {
        Myservice.save(colis,idhub,idF);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Colis> getColisByID(@PathVariable(value = "id") int Id)
            throws ResourceNotFoundException
    {
        return Myservice.getColisyId(Id);
    }


    @PutMapping("/updateColis")
    public Colis updateColis( @RequestBody Colis colis) throws ResourceNotFoundException {
        return Myservice.updateColis(colis);
    }


    @DeleteMapping("/DeleteColis/{id}")
    public Map<String, Boolean> deleteColis(@PathVariable(value = "id") int ColisId)
            throws ResourceNotFoundException

    {
        return Myservice.DeleteColis(ColisId);
    }


    @PutMapping("/colis/{id}")
    public ResponseEntity<Colis> updateColisWIthId(@PathVariable("id") int id,@RequestBody Colis colis)
    {
        return Myservice.updateColisWithId(id,colis);
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
        Myservice.export(response, idF,idS,idcolis,chiffreCodeBar,90,120,QR_CODE_IMAGE_PATH);

    }





    @GetMapping(value = "/genrateAndDownloadQRCode/{text}/{width}/{height}")
    public void download(
            @PathVariable("text") String QrCode,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        Service.generateQRCodeImage(QrCode,width, height, QR_CODE_IMAGE_PATH);
    }

    @GetMapping(value = "/genrateQRCode/{text}/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("text") String  QrCode,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(Service.getQRCodeImage(QrCode,width, height));
    }






    @GetMapping("/Generate/{idColis}")
    public String GenerateChiffreCodeBar(@PathVariable("idColis") int idColis)
    {
        String x = "";

        Colis colis=MyColisRepo.findById(idColis).orElse(null);
        if(colis.getEtat_colis().equals(Etat_colis.Livré))
        {
            x="01";
            System.out.println("chiffreCodeBar = " + x);

        }
        else if(colis.getEtat_colis().equals(Etat_colis.échange))
        {
            x="02";
            System.out.println("chiffreCodeBar = " + x);
        }
        else
        {
            x="00";
        }
        String var ="";
        String chiffreCodeBar ="";
        String idf ="";
        String idC="";
        for (Fournisseur F:colis.getFournisseurs())
        {
            if(idColis!=0)
            {
                idf= String.valueOf(F.getIdUser());
                var= String.valueOf(colis.getHub().getIdhub());
                System.out.println( var);
                idC= String.valueOf(colis.getIdColis());
                chiffreCodeBar = x.concat("0").concat(var).concat(idf).concat(idC);
                System.out.println("valeur final = " + chiffreCodeBar);
            }
        }
        colis.setCode_a_bar(chiffreCodeBar);
        MyColisRepo.save(colis);

        return chiffreCodeBar;


    }


























}
