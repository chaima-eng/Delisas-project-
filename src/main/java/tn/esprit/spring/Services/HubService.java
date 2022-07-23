package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entity.Anomalie_colis;
import tn.esprit.spring.Entity.Gouvernerat;
import tn.esprit.spring.Entity.Hub;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Repository.HubRepository;

@Service
public class HubService implements IHubService {
	@Autowired
	HubRepository hb;
	

	public Hub addhub(Hub h) {
		//List<String> gouvernerat = Stream.of(Gouvernerat.values()).map(Gouvernerat::name).collect(Collectors.toList());
		//gouvernerat.forEach(g->h.getGl().add(g));
	//	h.setGl(h.getGl());
		//List<Gouvernerat> days = Arrays.asList(Gouvernerat.values());
		//days.add(Gouvernerat.Ariana);
		//days.add(Gouvernerat.Ben_Arous);
		return hb.save(h);
	}
	
	@Override
	public List<Hub> retrieveHub() {
		return (List<Hub>) hb.findAll();
	}
	/*@Override
	public List<Hub> listeDeGouverneratParHub(Gouvernerat gouvernerat) {
		return hb.findByGouvernerats(gouvernerat);
	}*/
	
	@Override
	public void deleteHub(int id_Hub) {
		hb.deleteById(id_Hub);
	}
	
	@Override
	public Hub updateHub(Hub h, int id_Hub) {
		Hub hh = hb.findById(id_Hub).orElse(null);
			hh.setAdresse_hub(h.getAdresse_hub());
			hh.setGouvernerat_hub(h.getGouvernerat_hub());
			hh.setGouvernerat_list(h.getGouvernerat_list());
			return hb.save(hh);
	}
}
