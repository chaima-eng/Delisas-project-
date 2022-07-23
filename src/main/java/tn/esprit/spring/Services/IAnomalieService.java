package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Anomalie_colis;

public interface IAnomalieService {

	void deleteAnomalie(int id_Anomalie);

	List<Anomalie_colis> retrieveAnomalie();

	Anomalie_colis addAnomalie(Anomalie_colis a);

	Anomalie_colis updateAnomalie(Anomalie_colis a, int id_Anomalie);

}
