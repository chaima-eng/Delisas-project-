package com.example.backend.Controller;

import com.example.backend.Exception.ResponseMessage;
import com.example.backend.Service.ExcelHelper;
import com.example.backend.Service.ExcelService;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins="*")
@RequestMapping("/api/Excel")
@RestController
public class ExcelController {

    @Autowired
    ExcelService fileService;













}







