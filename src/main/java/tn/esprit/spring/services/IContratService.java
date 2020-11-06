package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;


public interface IContratService {
	
	
	public List<Contrat> getAllContrats();

	public Contrat ajouterContrat(Contrat contrat);

	public void deleteContratByRef(int contratRef);

	public Contrat getContratByRef(int contratRef);

	public void affecterContratAEmploye(int contratId, int employeId);

	public Contrat updateContrat(Contrat contrat) ;

	public Contrat ajoutContrat(Contrat contrat);

	
	

	
}
