package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Entity.Gouvernerat;
import tn.esprit.spring.Entity.Hub;

public interface IHubService {

	Hub addhub(Hub h);

	List<Hub> retrieveHub();

	void deleteHub(int id_Hub);

	

	Hub updateHub(Hub h, int id_Hub);

	//List<Hub> listeDeGouverneratParHub(Gouvernerat gouvernerat);

	//Hub addhub(Hub h, Gouvernerat gv);

}
