package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Repository.SocieteRepository;

@Service
public class SocieteService implements ISocieteService{
	@Autowired
	SocieteRepository societeRepository;
	
	
	public Societe addSociete(Societe s) {
		return societeRepository.save(s);
	}
	
	@Override
	public void deleteSociete(int id) {
		societeRepository.deleteById(id);
	}
	
	@Override
	public Societe updateSociete(Societe s, int id) {
		Societe societe = societeRepository.findById(id).orElse(null);
		societe.setLogo(s.getLogo());
		societe.setNom(s.getNom());
		societe.setTel(s.getTel());
		societe.setAdresse(s.getAdresse());
		societe.setAdresse_Mail(s.getAdresse_Mail());
		societe.setMatricule_Fiscale(s.getMatricule_Fiscale());
			
			return societeRepository.save(societe);
	}
	
	@Override
	public List<Societe> retrieveSociete() {
		return (List<Societe>) societeRepository.findAll();
	}
}
