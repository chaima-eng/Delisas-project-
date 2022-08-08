package tn.esprit.spring.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Etat_colis;
import tn.esprit.spring.Entity.Personnel;
import tn.esprit.spring.Entity.Pickup;
import tn.esprit.spring.Entity.Role;
import tn.esprit.spring.Repository.IntColisRepo;
import tn.esprit.spring.Repository.IntPersonnelRepo;
import tn.esprit.spring.Repository.PickupRepository;

@Service
public class PickupService implements IPickupService{
	@Autowired
	PickupRepository pr;
	@Autowired
	IntColisRepo cr;
	@Autowired
	IntPersonnelRepo plr;
	float SommeTotal;
	 //private LocalDate localDate = LocalDate.now();
	
	
		@Override
		public String ajouterEtaffecterpickupcolis(Pickup pickup, int idUser, List<Integer> idColis) {
			
			List<Colis> colis =cr.findAllById(idColis);
			Personnel p = plr.findById((int) idUser).orElse(null);
			if(p.getRole()==Role.Livreur){
				for (Integer id:idColis){
					pr.save(pickup);
				    pickup.setPersonnel(p);
				    Colis c = cr.findById(id).orElse(null);
				    c.getPickups().add(pickup);
				    SommeTotal=0;
				    idColis.forEach((n) -> {
						System.out.println(n);
						SommeTotal=this.findByTotale((int) n)+SommeTotal;
						System.out.println("Somme Totale : "+SommeTotal);
						 pickup.setPrix_total(SommeTotal);	
					});
				    c.setEtat_colis(Etat_colis.En_cours_enlevement);
				    pr.save(pickup);
				    }
				
				return "Ajouter Avec Success";
				}else
				{return "veuillez choisir un livreur";}
			}
		
		
		@Override
		public List<Pickup> retrievePickup() {
			return (List<Pickup>) pr.findAll();
		}
		
		
		@Override
		public List<Colis> listedeColis(int idPickup) {
			return cr.findByPickupsIdPickup(idPickup);	
		}
		
		
		@Override
		public float Sommepickup(int idPickup) {

			return cr.Sommepickup(idPickup);}
		
		
		@Override
		public List Listids(int idPickup) {

			return cr.Listids(idPickup);}
		
		
		@Override
		public float SommeTotalColis(int idPickup){
			SommeTotal=0;
			List Lister;
			Lister=this.Listids(idPickup);
			Lister.forEach((n) -> {
				System.out.println(n);
				SommeTotal=this.findByTotale((int) n)+SommeTotal;
				System.out.println("Somme Totale : "+SommeTotal);

				 Pickup p = pr.findById(idPickup).orElse(null);	
				 p.setPrix_total(SommeTotal);	
			});
			
		 return SommeTotal;
		}
		
		
		@Override
		public String updatepickup(Pickup pickup, int idPickup) {
		
			Pickup p = pr.findById(idPickup).orElse(null);
			p.setPrix_total(this.SommeTotalColis((int) idPickup));
			return "modif";
		}
		
		
		@Override
		public float findByTotale(int idColis) {

			return cr.findByTotale(idColis);}
		
		
		@Override
		public List<Colis> retrieveColis() {
			return (List<Colis>) cr.findAll();
		}
		/*
		@Override
		public List<Colis> findByFournisseursNomsociete(String nomsociete) {
			return cr.findByFournisseursNomsociete(nomsociete);	
		}*/
		

}


