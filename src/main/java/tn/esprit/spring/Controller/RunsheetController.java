package tn.esprit.spring.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import com.itextpdf.barcodes.qrcode.WriterException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entity.Colis;
import tn.esprit.spring.Entity.Personnel;
import tn.esprit.spring.Entity.Pickup;
import tn.esprit.spring.Entity.Runsheet;
import tn.esprit.spring.Entity.Societe;
import tn.esprit.spring.Repository.RunsheetRepository;
import tn.esprit.spring.Services.IPickupService;
import tn.esprit.spring.Services.IRunsheetService;

@CrossOrigin(origins="*")
@RequestMapping("/runsheet")
@RestController
public class RunsheetController {
	@Autowired
	IRunsheetService rs;

	@Autowired
	RunsheetRepository rr;
	
	
	@PostMapping("/ajouterEtaffectercRunsheet/{codeabar}/{idUser}")
	@ResponseBody
	public String ajouterEtaffectercRunsheet(@RequestBody Runsheet r, @RequestParam("code_a_bar") List<String>  codeabar, @PathVariable("idUser") int idUser) {
		return rs.ajouterEtaffectercRunsheet(r, codeabar, idUser);
	}
	
	@PutMapping("/updateRunsheet/{idRunsheet}")
	@ResponseBody
	void updateRunsheet(@RequestBody Runsheet r,@PathVariable("idRunsheet") int idRunsheet){
		 rs.updateRunsheet(r,idRunsheet);
	}

	@GetMapping("/retrieve-Runsheet")
	@ResponseBody
	List<Runsheet> retrieveSociete(){
	return rs.retrieveRunsheet();
	}
	
	@DeleteMapping("/delete-Runsheet/{idRunsheet}")
	void deleteSociete(@PathVariable(name="idRunsheet")int idRunsheet){
		rs.deleteRunsheet(idRunsheet);
	}
	
	@GetMapping("/retrieveColis/{codeabar}")
	@ResponseBody
	 Colis retrieveColis(@PathVariable(name="codeabar")String codeabar) {
		return rs.retrieveColis(codeabar);
	}
	
	@GetMapping("/getnbrColis/{idRunsheet}")
	@ResponseBody
	 int getnbrColis(@PathVariable(name="idRunsheet")int idRunsheet) {
		return rs.getnbrColis(idRunsheet);
	}
	
	@GetMapping("/getPersonnel/{username}")
	@ResponseBody
	 Personnel getPersonnel(@PathVariable(name="username")String username) {
		return rs.getPersonnel(username);
	}
	
	@GetMapping("/retrieve-Personnels")
	@ResponseBody
	List<Personnel> getPersonnels(){
	return rs.getPersonnels();
	}


	@GetMapping("/colis/export/pdf")
	public void exportToPDF(HttpServletResponse response, int idrunsheet, int idS,int idP)
			throws DocumentException, IOException, WriterException {

		Runsheet r=rr.findById(idrunsheet).orElse(null);
		String chiffreCodeBar = r.getCodeabarre();

		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=runsheet" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		rs.export(response,idrunsheet,idS,idP, chiffreCodeBar,90,120);

	}

















}
