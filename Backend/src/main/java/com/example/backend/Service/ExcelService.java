package com.example.backend.Service;


import com.example.backend.Entity.Colis;
import com.example.backend.Repository.IntColisRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
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
@Slf4j
public class ExcelService {

    @Autowired
    private IntColisRepo MyColisRepo;


    public void saveCommunes(MultipartFile file) {
        try {
            List<Colis> communes = ExcelHelper.excelToCommunes(file.getInputStream());
            StopWatch watch = new StopWatch();
            watch.start();
            MyColisRepo.saveAll(communes);
            watch.stop();
            log.info("Save communes {} time Elapsed: {}", communes.size());
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }






}
