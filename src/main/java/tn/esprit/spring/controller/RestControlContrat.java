package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

@RestController
public class RestControlContrat {

	@Autowired
    IContratService icontratservice;
	
	
	
    @DeleteMapping("/deleteContratByRef/{contratRef}") 
	@ResponseBody 
	public void deleteContratByRef(@PathVariable("contratRef")int contratRef)
	{
    	icontratservice.deleteContratByRef(contratRef);
	}
    
    @GetMapping(value = "/getContratByRef/{contratRef}")
    @ResponseBody
	public Contrat getContratByRef(@PathVariable("contratRef") int contratRef) {

		return icontratservice.getContratByRef(contratRef);
	}
    
    @GetMapping(value = "/getContratByRef")
    @ResponseBody
	public List<Contrat> getAllContrats() {

		return icontratservice.getAllContrats();
	}
    
    @PutMapping(value = "/affectContratAEmploye/{idcontrat}/{idemp}") 
 	public void affecterContratAEmploye(@PathVariable("idcontrat")int contratId, @PathVariable("idemp")int employeId)
 	{
    	icontratservice.affecterContratAEmploye(contratId, employeId);
 	}
	
}
