package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

@Controller
public class ControllerContratImpl {

	@Autowired
    IContratService icontratservice;
	
	public int ajouterContrat(Contrat contrat) {
		icontratservice.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	public void deleteContratByRef(int contratRef)
	{
		icontratservice.deleteContratByRef(contratRef);
	}
	
	public Contrat getContratByRef(int contratRef) {

		return icontratservice.getContratByRef(contratRef);
	}
	
	public List<Contrat> getAllContrats(){
		return icontratservice.getAllContrats();
	}
	
	public void affecterContratAEmploye(int contratId, int employeId)
	{
		icontratservice.affecterContratAEmploye(contratId, employeId);
	}
}
