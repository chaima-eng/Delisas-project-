package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Etat_debrief;
import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.Personnel;
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


}

