package com.example.backend.Service;


import com.example.backend.Entity.Colis;
import com.example.backend.Repository.IntColisRepo;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Service
public class ExcelService {

    @Autowired
    private IntColisRepo MyColisRepo;


/*

    public ByteArrayInputStream load() {
        List<Colis> colis = MyColisRepo.findAll();

        ByteArrayInputStream in = ExcelHelper.ToExcel(colis);
        return in;
    }




 */






}
