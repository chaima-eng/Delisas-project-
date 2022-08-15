package tn.esprit.spring.Services;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.barcodes.qrcode.WriterException;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Etat_colis;
import tn.esprit.spring.Entity.Etat_debrief;
import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.Personnel;
import tn.esprit.spring.Entity.Role;
import tn.esprit.spring.Entity.Runsheet;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Entity.User;
import tn.esprit.spring.Repository.IntColisRepo;
import tn.esprit.spring.Repository.IntFournisseurRepo;
import tn.esprit.spring.Repository.IntPersonnelRepo;
import tn.esprit.spring.Repository.RunsheetRepository;
import tn.esprit.spring.Repository.SocieteRepository;


@Service
public class RunsheetSercvice implements IRunsheetService {
	@Autowired
	RunsheetRepository rr;
	@Autowired
	IntColisRepo cr;
	@Autowired
	IntPersonnelRepo pr;
	@Autowired
    private SocieteRepository MysRepo;
    @Autowired
    private IntFournisseurRepo MyFRepo;
	float SommeTotal;
	

	    
	@Override
	public String ajouterEtaffectercRunsheet(Runsheet r, List<String> codeabar, int idUser) {
	
		Personnel p = pr.findById((int) idUser).orElse(null);
		for (String codeabar1:codeabar){
			rr.save(r);
			r.setEtat_debrief(Etat_debrief.non_cloturé);
			r.setPersonnel(p);
		    Colis c = cr.findByCodeabar(codeabar1);
		    c.getRunsheets().add(r);
		    SommeTotal=0;
		    //int idColis;
		   // List<Integer> idColis = null;
		    //List<Colis> colis = cr.findAllById(idColis);
		    codeabar.forEach((n) -> {
				System.out.println(n);
				SommeTotal=this.findByTotal((String) n)+SommeTotal;
				System.out.println("Somme Totale : "+SommeTotal);
				 r.setPrixtotal(SommeTotal);	
			});
		    
		    
		    
		    
		    
		    
		    String x,y = "";

	      //  Runsheet runsheet=rr.findById(idRunsheet).orElse(null);

	        if(r.getIdRunsheet()!=0)
	        {
	            y="1";
	        }



	        if(r.getEtat_debrief().equals(Etat_debrief.non_cloturé))
	        {
	            x="01";
	            System.out.println("chiffreCodeBar = " + x);

	        }
	        else if(r.getEtat_debrief().equals(Etat_debrief.cloturé))
	        {
	            x="02";
	            System.out.println("chiffreCodeBar = " + x);
	        }
	        else
	        {
	            x="00";
	        }
	       // String var ="";
	        String chiffreCodeBar ="";
	        String idf ="";
	        String idC="";
	       
	            if(r.getIdRunsheet()!=0)
	            {
	                idf= String.valueOf(p.getIdUser());
	               // var= String.valueOf(r.getHub().getIdhub());
	                //System.out.println( var);
	                idC= String.valueOf(r.getIdRunsheet());
	                chiffreCodeBar = y.concat(x).concat("0").concat(idf).concat(idC);
	                System.out.println("valeur final = " + chiffreCodeBar);
	            }
	        
	        r.setCodeabarre(chiffreCodeBar);
		    
	        c.setEtat_colis(Etat_colis.En_cours_livraison);
		    
		    
		rr.save(r);
}
		return "Ajouter Avec Success";
	}
	
	
	@Override
	public float findByTotal(String codeabar) {

		return cr.findByTotal(codeabar);}
	
	@Override
	public List Listid(int idRunsheet) {

		return cr.Listid(idRunsheet);}

	@Override
	public void updateRunsheet(Runsheet r, int idRunsheet) {
		//User u = userRepository.findById(userId).orElse(null);
	Runsheet runsheet = rr.findById(idRunsheet).orElse(null);
	runsheet.setEtat_debrief(r.getEtat_debrief());
	
	
	/* String x,y = "";

     //  Runsheet runsheet=rr.findById(idRunsheet).orElse(null);

       if(r.getIdRunsheet()!=0)
       {
           y="1";
       }



       if(r.getEtat_debrief().equals(Etat_debrief.non_cloturé))
       {
           x="01";
           System.out.println("chiffreCodeBar = " + x);

       }
       else if(r.getEtat_debrief().equals(Etat_debrief.cloturé))
       {
           x="02";
           System.out.println("chiffreCodeBar = " + x);
       }
       else
       {
           x="00";
       }
      // String var ="";
       String chiffreCodeBar ="";
       String idf ="";
       String idC="";
       Personnel p = null ;
           if(r.getIdRunsheet()!=0)
           {
               idf= String.valueOf(p.getIdUser());
              // var= String.valueOf(r.getHub().getIdhub());
               //System.out.println( var);
               idC= String.valueOf(r.getIdRunsheet());
               chiffreCodeBar = y.concat(x).concat("0").concat(idf).concat(idC);
               System.out.println("valeur final = " + chiffreCodeBar);
           }
       
       r.setCodeabarre(chiffreCodeBar);*/
       rr.save(r);	
	}
	
	@Override
	public void deleteRunsheet(int idRunsheet) {
		rr.deleteById(idRunsheet);
	}
	
	
	
	@Override
	public List<Runsheet> retrieveRunsheet() {
		
		return (List<Runsheet>) rr.findAll();
	}
	
	@Override
	public Colis retrieveColis(String codeabar) {
		return cr.findByCodeabar(codeabar);
	}
	
	@Override
	public int getnbrColis(int idRunsheet) {
		return cr.getnbrColis(idRunsheet);
	
	}

	public String GenerateChiffreCodeBar(@PathVariable("idRunsheet") int idRunsheet)
    {  
		int idUser = 0;
		Personnel p = pr.findById((int) idUser).orElse(null);
		 Runsheet r = rr.findById(idRunsheet).orElse(null);
		String x,y = "";

    //  Runsheet runsheet=rr.findById(idRunsheet).orElse(null);

      if(r.getIdRunsheet()!=0)
      {
          y="1";
      }



      if(r.getEtat_debrief().equals(Etat_debrief.non_cloturé))
      {
          x="01";
          System.out.println("chiffreCodeBar = " + x);

      }
      else if(r.getEtat_debrief().equals(Etat_debrief.cloturé))
      {
          x="02";
          System.out.println("chiffreCodeBar = " + x);
      }
      else
      {
          x="00";
      }
     // String var ="";
      String chiffreCodeBar ="";
      String idf ="";
      String idC="";
     
          if(r.getIdRunsheet()!=0)
          {
              idf= String.valueOf(p.getIdUser());
             // var= String.valueOf(r.getHub().getIdhub());
              //System.out.println( var);
              idC= String.valueOf(r.getIdRunsheet());
              chiffreCodeBar = y.concat(x).concat("0").concat(idf).concat(idC);
              System.out.println("valeur final = " + chiffreCodeBar);
          }
      
      r.setCodeabarre(chiffreCodeBar);
      rr.save(r);
      return chiffreCodeBar;


    }
	
	   @Override
	    public Personnel getPersonnel(String username) {
	        return pr.findByUserName(username);
	    }
	   
	   @Override
	    public List<Personnel> getPersonnels() {

	        List p =new ArrayList();
	        for (Personnel personnel:pr.findAll())
	        {

	            if(personnel.getRole()==Role.Livreur)
	            {
	               p.add(personnel);
	            }
	        }
	            return p;

	    }

	@Override
	public void export(HttpServletResponse response, int idS, int idrunsheet,int IdP,String chiffreBarcode, int width, int height) throws DocumentException, IOException, WriterException {


		Runsheet runsheet = rr.findById(idrunsheet).orElse(null);
		Societe Soc = MysRepo.findById(idS).orElse(null);

		Personnel personnel = pr.findById(IdP).orElse(null);

		runsheet.getIdRunsheet();

		String image= Soc.getLogo();
		//chiffreCodeBar = runsheet.getCodeabarre();

		Document document = new Document(PageSize.A4);
		PdfWriter docWriter = null;

		docWriter=PdfWriter.getInstance(document, response.getOutputStream());



		document.open();
		//barcode

		PdfContentByte cb = docWriter.getDirectContent();

		Barcode128 code128 = new Barcode128();
		code128.setCode(runsheet.getCodeabarre().trim());
		code128.setCodeType(Barcode128.CODE128);
		Image code128Image = code128.createImageWithBarcode(cb, null, null);
		code128Image.setRight(90);
		code128Image.setAbsolutePosition(400,700);
		code128Image.scalePercent(125);
		document.add(code128Image);




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

		p3.setAlignment(Paragraph.ALIGN_BASELINE);

		p4.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(p3);
		document.add(p4);
		document.add(p5);
		document.add(p6);


		/*--Title-*/
		Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font2.setSize(20);

		Paragraph p2 = new Paragraph(" Bordereau de livraison N° : "+runsheet.getCodeabarre(), font2);
		p2.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p2);

		//For SPacing
		Table tab= new Table(3);
		tab.setBorderColor(Color.DARK_GRAY);
		document.add(tab);



		Font font8 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font8.setSize(12);
		Paragraph p7 =
				new Paragraph("Je soussigné chauffeur chargé par DELISAS " +
						"à livrer les colis ci-dessous et à encaisser les prix auprès " +
						"des destination indiqués dans le présent planing," +
						"reconnais Être responsable des montants encaissés et mengage" +" "+
						"à les restituer en fin de journée" +" "+
						"sans différence ni manquant au service financier",font8 );

		p7.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(p7);



		document.add(tab);

		Font font4 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font4.setSize(12);

		Paragraph p9 = new Paragraph(" Nom Complet : "+personnel.getUserName(), font4);
		Paragraph p1 = new Paragraph(" CIN : "+personnel.getCin(), font4);
		Paragraph p0 = new Paragraph(" Matricule : "+personnel.getMatriculevehicule() , font4);


		document.add(p9);
		document.add(p1);
		document.add(p0);

		Paragraph pa = new Paragraph("Cachet société  : " , font3);
		pa.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(pa);

		document.add(tab);
		document.add(tab);
		document.add(tab);
		document.add(tab);





		Paragraph nbrColis = new Paragraph("Nombre des colis : "+ cr.getnbrColis(idrunsheet), font4);
		nbrColis.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(nbrColis);



		document.add(tab);
		document.add(tab);
		document.add(tab);
		document.add(tab);





		Font font5 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font5.setSize(15);
		font5.isItalic();
		font5.isBold();


		Paragraph l = new Paragraph("Liste des colis en cours de livraison : " , font2);
		l.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(l);

		document.add(tab);


		//Liste COlis
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);



		table.setHorizontalAlignment(PdfTable.ALIGN_LEFT);

		table.setWidths(new float[] {3.5f, 3.5f, 3.5f, 3.5f, 3.5f,3.5f, 3.5f, 3.5f});
		//table.setWidths(new float[] {3.5f});
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setPadding(6);


		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(10);


		cell.setPhrase(new Phrase("Code à barre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Expéditeur", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("N° Expéditeur", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Client", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("N° Client", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Adresse", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COD", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Remarque", font));
		table.addCell(cell);


		for (Colis F:runsheet.getColiss())

		{

			PdfContentByte cbs = docWriter.getDirectContent();

			Barcode128 code128s = new Barcode128();
			code128s.setCode(F.getCodeabar().trim());
			code128s.setCodeType(Barcode128.CODE128);
			Image code128Images = code128s.createImageWithBarcode(cbs, null, null);
			table.addCell(code128Images);




			for (Fournisseur v:F.getFournisseurs())
			{

				table.addCell(v.getLastName());
				table.addCell(String.valueOf(v.getTel()));
			}


			table.addCell(F.getNom_complet_client());
			table.addCell(String.valueOf(F.getNum_tel()));

			table.addCell(F.getAdresse_client());
			table.addCell(String.valueOf(F.getCode_colis()));
			table.addCell(F.getRemarque());
			document.add(table);

		}


		document.add(tab);

		Paragraph total = new Paragraph(" Total : "+ cr.Sommerunsheet(idrunsheet), font4);
		total.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(total);







		document.close();

	}














}

