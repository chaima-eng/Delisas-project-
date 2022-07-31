package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Societe;

public interface ISocieteService {


	Societe addSociete(Societe s);

	void deleteSociete(int id);

	Societe updateSociete(Societe s, int id);

	List<Societe> retrieveSociete();

}
