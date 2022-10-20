package com.example.backend.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.example.backend.Entity.Colis;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelHelper {



    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "commune";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Colis> excelToCommunes(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Colis> communes = new ArrayList<Colis>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Colis commune = new Colis();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            commune.setNom_complet_client(currentCell.getStringCellValue());
                            break;
                        case 1:
                            commune.setService_colis(currentCell.getStringCellValue());
                            break;
                        case 2:
                            commune.setAdresse_client(currentCell.getStringCellValue());
                            break;
                        case 3:
                            commune.setPoids((float) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            commune.setCode_colis((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            commune.setLongueur((int) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            commune.setLargeur((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            commune.setHauteur((int) currentCell.getNumericCellValue());

                            break;
                        case 8:
                            commune.setDelegation_client( currentCell.getStringCellValue());
                            break;
                        case 9:
                            commune.setRemarque( currentCell.getStringCellValue());
                            break;
                        case 10:
                            commune.setNum_tel((int) currentCell.getNumericCellValue());
                            break;
                        case 11:
                            commune.setMode_paiement( currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                communes.add(commune);
            }
            workbook.close();
            return communes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }










}
