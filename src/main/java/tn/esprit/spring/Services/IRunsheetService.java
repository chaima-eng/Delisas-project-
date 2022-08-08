package tn.esprit.spring.Services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;


import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Runsheet;

public interface IRunsheetService {

	String ajouterEtaffectercRunsheet(Runsheet r, List<String> code_a_bar, int idUser);

	List Listid(int idRunsheet);

	//float findByTotal(int idColis);

	float findByTotal(String codeabar);

	void updateRunsheet(Runsheet r, int idRunsheet);

	void deleteRunsheet(int idRunsheet);

	List<Runsheet> retrieveRunsheet();

	Colis retrieveColis(String codeabar);

	int getnbrColis(int idRunsheet);

	
}
