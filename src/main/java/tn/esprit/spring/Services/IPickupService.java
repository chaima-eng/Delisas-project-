package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Pickup;

public interface IPickupService {

	

	//void ajouterEtAffecterPickupColisPersonnel(Pickup pickup, List<Integer> idColis);

	//String ajouterEtaffectercomplaints(Pickup pickup, int idUser);

//	void ajouterEtaffecterpickup(Pickup pickup, int idUser);

	//void assigncolisTopersonnel(int idPickup, int idcolis);

	//void assigncolisTopersonnel(int idPickup, List<Integer> idcolis);

	//void assigncolisTopickup(int idPickup, List<Integer> idColis);

	//void ajouterEtaffecterpickup(Pickup pickup, int idUser, List<Integer> idColis);

	//void ajouterEtaffecterpickup(Pickup pickup, int idUser);

//	void ajouterEtaffecterpickupcolis(Pickup pickup, List<Integer> idColis);

	String ajouterEtaffecterpickupcolis(Pickup pickup, int idUser, List<Integer> idColis);

	List<Pickup> retrievePickup();

	List<Colis> listedeColis(int idPickup);

	float Sommepickup(int idPickup);
	
	List Listids(int idPickup);
	
	float findByTotale(int idColis);
	float SommeTotalColis(int idPickup);

	String updatepickup(Pickup pickup, int idPickup);

	List<Colis> retrieveColis();

}
