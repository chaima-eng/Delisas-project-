package com.example.backend.Service;

import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Etat_colis;
import com.example.backend.Entity.Personnel;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntColisRepo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
@Slf4j
public class ColisService implements IntColisService {

    private LocalDate localDate = LocalDate.now();

    private int width=70;
    private int height=70;

    @Autowired
    private IntColisRepo MyColisRepo;
    @Override
    public Colis save(Colis colis) {
        colis.setDate_livraison(localDate.plusDays(1));
        colis.setEtat_colis(Etat_colis.crée);
        return MyColisRepo.save(colis);
    }
    @Override
    public ResponseEntity<Colis> getColisyId(int Id)
            throws ResourceNotFoundException {
        Colis colis = MyColisRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("colis not found for this id :: " + Id));
        return ResponseEntity.ok().body(colis);
    }
    @Override
    public List<Colis> getAllColis() {
        return MyColisRepo
                .findAll();
    }


/*
    @Override
    public void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Enabled", font));
        table.addCell(cell);
    }


 */





    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
































    @Override
    public void export(HttpServletResponse response, int
            idcolis, String text, String filePath) throws DocumentException, IOException, WriterException {



        Colis colis = MyColisRepo.findById(idcolis).orElse(null);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        /*---*/
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
        font2.setSize(20);
      //  font2.setColor(Color.black);
        Paragraph p2 = new Paragraph("Delisas", font2);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p2);

        Font font3 = FontFactory.getFont(FontFactory.HELVETICA);
        font2.setSize(10);
        Paragraph p3 = new Paragraph("Code : "+" "+colis.getCode_colis(), font3);
        p3.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(p3);
      //  Border gb = (Border) new SolidBorder(com.itextpdf.kernel.color.Color.GRAY,1f/2f);


        Table table0= new Table(1);
        table0.setBorderColor(Color.DARK_GRAY);
        document.add(table0);




        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);



        table.setHorizontalAlignment(PdfTable.ALIGN_LEFT);

        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.black);
        cell.setPadding(5);


        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("nom client", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Designation", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("service", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Remarque", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nb piece", font));
        table.addCell(cell);
        table.addCell(colis.getNom_complet_client());
        table.addCell(colis.getDesignation_colis());
        table.addCell(colis.getService_colis());
        table.addCell(colis.getRemarque());
        table.addCell(String.valueOf(colis.getNb_piece()));


        document.add(table);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println(path);
        Image image = Image.getInstance(path.toString());
        document.add(image);


        document.close();



    }


    @Override
    public ResponseEntity<Colis> updateColis(int id, Colis colis) {

        Optional<Colis> ColisInfo = MyColisRepo.findById(id);
        if (ColisInfo.isPresent()) {
            Colis c = ColisInfo.get();
            c.setRemarque(colis.getRemarque());
            c.setNum_tel(colis.getNum_tel());
            c.setNum_tel_2(colis.getNum_tel_2());
            c.setLongueur(colis.getLongueur());
            c.setService_colis(colis.getService_colis());
            c.setNb_piece(colis.getNb_piece());
            c.setLargeur(colis.getLargeur());
            c.setHauteur(colis.getHauteur());
            c.setMode_paiement(colis.getMode_paiement());
            c.setAnomalieColis(colis.getAnomalieColis());
            c.setEtat_colis(colis.getEtat_colis());
            c.setNom_complet_client(colis.getNom_complet_client());
            c.setCode_colis(colis.getCode_colis());
            c.setDelegation_client(colis.getDelegation_client());
            c.setCompteur_anomalie(colis.getCompteur_anomalie());
            c.setAdresse_client(colis.getAdresse_client());
            c.setDate_livraison(colis.getDate_livraison());
            c.setLocalisation_colis(colis.getLocalisation_colis());

            return new ResponseEntity<>(MyColisRepo.save(colis), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }





    @Override
    public Map<String, Boolean> DeleteColis(int colisId) throws ResourceNotFoundException {
        Colis colis = MyColisRepo.findById(colisId)
                .orElseThrow(() -> new ResourceNotFoundException(" not found   :: " + colisId));
        MyColisRepo.delete(colis);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }























}
