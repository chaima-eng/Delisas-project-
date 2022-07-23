package com.example.backend.Service;

import com.example.backend.Entity.Colis;
import com.example.backend.Entity.Etat_colis;
import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Societe;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntColisRepo;
import com.example.backend.Repository.IntFournisseurRepo;
import com.example.backend.Repository.SocieteRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.zxing.Writer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
@Slf4j
public class ColisService implements IntColisService {


   // private static final String IMAGE_PATH = "./src/main/resources/Logo.jpg";
    private LocalDate localDate = LocalDate.now();

    private int width=70;
    private int height=70;
    @Autowired
    ServletContext context;

    @Autowired
    private IntColisRepo MyColisRepo;
    @Autowired
    private SocieteRepository MysRepo;
    @Autowired
    private IntFournisseurRepo MyFRepo;
    @Override
    public Colis save(Colis colis) {
        colis.setDate_livraison(localDate.plusDays(1));
        colis.setEtat_colis(Etat_colis.crée);
        if((colis.getLargeur()== 0)&&(colis.getLongueur()== 0)&&(colis.getHauteur()==0))
        {
            colis.setLargeur(1);
            colis.setLongueur(1);
            colis.setHauteur(1);
        }

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
    public void export(HttpServletResponse response, int idcolis, String text, String filePath, int width, int height) throws DocumentException, IOException, WriterException {

    }

    @Override
    public List<Colis> getAllColis() {
        return MyColisRepo
                .findAll();
    }

    @Override
    public void export(HttpServletResponse response,int idF ,int idS,int idcolis, String chiffreCodeBar, int width, int height, String filePath) throws DocumentException, IOException, WriterException {

        Colis colis = MyColisRepo.findById(idcolis).orElse(null);
        Societe Soc = MysRepo.findById(idS).orElse(null);
        Fournisseur F = MyFRepo.findById(idF).orElse(null);
        String image= Soc.getLogo();
        chiffreCodeBar = colis.getCode_a_bar();
        Document document = new Document(PageSize.A4);



        PdfWriter.getInstance(document, response.getOutputStream());



        document.open();
        /*--Title-*/
        Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font2.setSize(20);
        //  font2.setColor(Color.black);
        Paragraph p2 = new Paragraph(" Bordereau de livraison N° : "+colis.getCode_a_bar(), font2);
        p2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p2);

        /*-General information of the delivery company and logo-*/
        Font font3 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font3.setSize(12);

        Paragraph p3 = new Paragraph(" Societé : "+Soc.getNom() , font3);
        Paragraph p4 = new Paragraph(" Matricule Fiscale:"+Soc.getMatricule_Fiscale() , font3);
        Paragraph p5 = new Paragraph(" Adresse : "+Soc.getAdresse() , font3);
        Paragraph p6 = new Paragraph(" Téléphone :"+Soc.getTel() , font3);

        String path0 ="./src/main/resources/Logo.jpg";

        Path lofopath = FileSystems.getDefault().getPath(path0);

        Image image3 = Image.getInstance(lofopath.toString());


        document.add(image3);

        p3.setAlignment(Paragraph.ALIGN_LEFT);

        p4.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);

        //For spacing
        Table tab= new Table(1);
        tab.setBorderColor(Color.DARK_GRAY);
        document.add(tab);



        Paragraph p0 = new Paragraph("cachet : " , font2);
        document.add(p0);

        document.add(tab);

        //Table 0

        PdfPTable table0 = new PdfPTable(2);
        table0.setWidthPercentage(100f);



        table0.setHorizontalAlignment(PdfTable.ALIGN_LEFT);

        table0.setWidths(new float[] {3.5f, 3.5f});
        table0.setSpacingBefore(10);

        PdfPCell cell0 = new PdfPCell();
        cell0.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);

        cell0.setPhrase(new Phrase("Coordonnés expéditeur", font));
        table0.addCell(cell0);
        cell0.setPhrase(new Phrase("Coordonnées destinataire", font));
        table0.addCell(cell0);


        table0.addCell("Nom Societé : "+ F.getNom_societe());
        table0.addCell("Nom et Prénom : " +colis.getNom_complet_client());
        table0.addCell("Adresse : "+F.getAdresse_societe() );
        table0.addCell("Téléphone 1 : "+colis.getNum_tel());

        table0.addCell("Governorat : "+F.getGouvernerat() );
        table0.addCell("Téléphone 2 :  "+colis.getNum_tel_2());
        table0.addCell("Adresse : "+colis.getAdresse_client() );
        table0.addCell("Téléphone : "+F.getTel() );


        document.add(table0);








        //FirstTable
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);



        table.setHorizontalAlignment(PdfTable.ALIGN_LEFT);

        table.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);





        cell.setPhrase(new Phrase("Date de création", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Longueur", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Largeur", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Hauteur", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Piéces", font));
        table.addCell(cell);
        table.addCell(String.valueOf(colis.getDate_livraison()));
        table.addCell(String.valueOf(colis.getLongueur()));
        table.addCell(String.valueOf(colis.getLargeur()));
        table.addCell(String.valueOf(colis.getHauteur()));
        table.addCell(String.valueOf(colis.getNb_piece()));
        document.add(table);


        //For spacing
        document.add(tab);


        //Seconde one

        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(100f);



        table2.setHorizontalAlignment(PdfTable.ALIGN_LEFT);

        table2.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table2.setSpacingBefore(10);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(5);

        int tva =13;
        int PHT= (int) (colis.getQuantite()*0.87);

        cell2.setPhrase(new Phrase("Désignation", font));
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("Quantité", font));
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("Prix Hors Taxe", font));
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("TVA", font));
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("Total", font));
        table2.addCell(cell2);
        table2.addCell(colis.getDesignation_colis());
        table2.addCell(String.valueOf(colis.getQuantite()));
        table2.addCell(String.valueOf(PHT));
        table2.addCell(String.valueOf(tva).concat("%"));
        table2.addCell(String.valueOf(colis.getTotal()));
        document.add(table2);












        /*
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(chiffreCodeBar, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println(path);
        Image image2 = Image.getInstance(path.toString());
        document.add(image2);


         */







        document.close();
    }

    @Override
    public  byte[] getBarCodeImage(String text, int width, int height) {
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.EAN_13, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public ResponseEntity<Response> addSociete(MultipartFile file, String Societe) throws JsonParseException, JsonMappingException, Exception {
        System.out.println("Ok .............");
        Societe s = new ObjectMapper().readValue(Societe, Societe.class);

        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mkdir success.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }




        s.setLogo(newFileName);

        Societe art = MysRepo.save(s);



        if (art != null)
        {
            return new ResponseEntity<Response>( HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }
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



    public static void generateQRCodeImage(String text,int width, int height, String filePath)

            throws WriterException, IOException

    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    //this method will generate the QR Code in the form of a byte array, which we used to return as a response for HTTP Request

    public static byte[] getQRCodeImage(String text,int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }


    //from chaima this methode is to check !

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





    //Just for testing the pdf





















}
