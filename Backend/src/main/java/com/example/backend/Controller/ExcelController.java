package com.example.backend.Controller;

import com.example.backend.Entity.Colis;
import com.example.backend.Exception.ResponseMessage;
import com.example.backend.Repository.IntColisRepo;
import com.example.backend.Service.ColisService;
import com.example.backend.Service.ExcelHelper;
import com.example.backend.Service.ExcelService;
import com.example.backend.Service.IntColisService;
import io.swagger.models.Model;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins="*")
@RequestMapping("/api/Excel")
@RestController
public class ExcelController {



    @Autowired
    IntColisService Myservice;
    @Autowired
    private IntColisRepo MyColisRepo;


    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=colis" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Colis> listColis =Myservice.getAllColis();

        ColisService excelExporter = new ColisService(listColis);

        excelExporter.exportExel(response);
    }


    @RequestMapping(value = "/upload-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Colis>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Colis> colisList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Colis colis = new Colis();

                XSSFRow row = worksheet.getRow(index);
              //  Integer id = (int) row.getCell(0).getNumericCellValue();

                colis.setNom_complet_client(row.getCell(0).getStringCellValue());
                colis.setService_colis(row.getCell(1).getStringCellValue());
                colis.setAdresse_client(row.getCell(2).getStringCellValue());
                colis.setPoids(row.getCell(3).getNumericCellValue());
                colis.setCode_colis(row.getCell(4).getNumericCellValue());
                colis.setLongueur(row.getCell(5).getNumericCellValue());
                colis.setLargeur(row.getCell(6).getNumericCellValue());
                colis.setHauteur(row.getCell(7).getNumericCellValue());


                colis.setDelegation_client(row.getCell(9).getStringCellValue());
                colis.setRemarque(row.getCell(11).getStringCellValue());

                colis.setNum_tel(row.getCell(13).getNumericCellValue());

                colis.setMode_paiement(row.getCell(15).getStringCellValue());
                colisList.add(colis);
                MyColisRepo.save(colis);
             }

        }


        return new ResponseEntity<>(colisList, status);
    }









}







