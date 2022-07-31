package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entity.Runsheet;

public interface IRunsheetService {

	String ajouterEtaffectercRunsheet(Runsheet r, List<String> code_a_bar, int idUser);

	List Listid(int idRunsheet);

	//float findByTotal(int idColis);

	float findByTotal(String codeabar);

	void updateRunsheet(Runsheet r, int idRunsheet);

	void deleteRunsheet(int idRunsheet);

	List<Runsheet> retrieveRunsheet();

}
