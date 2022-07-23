package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Repository.AnomalieRepository;

@Service
public class AnomalieService implements IAnomalieService{
	@Autowired
	 AnomalieRepository  anomalieRepository;
	
	@Override
	public Anomalie_colis addAnomalie(Anomalie_colis a) {
		return anomalieRepository.save(a);
	}
	
	@Override
	public void deleteAnomalie(int id_Anomalie) {
		anomalieRepository.deleteById(id_Anomalie);
	}
	
	
	@Override
	public List<Anomalie_colis> retrieveAnomalie() {
		return (List<Anomalie_colis>) anomalieRepository.findAll();
}
	
	@Override
	public Anomalie_colis updateAnomalie(Anomalie_colis a, int id_Anomalie) {
		Anomalie_colis ac = anomalieRepository.findById(id_Anomalie).orElse(null);
			ac.setAcronyme_Anomalie(a.getAcronyme_Anomalie());
			ac.setDesignation_Anomalie(a.getDesignation_Anomalie());
			ac.setNbr_tentatives(a.getNbr_tentatives());
			return anomalieRepository.save(ac);
	}
}
