package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public Entreprise ajouterEntreprise(Entreprise entreprise);
	public Entreprise updateEntreprise(Entreprise entreprise);
	public Entreprise getEntrepriseById(int entrepriseId);
	public List<Entreprise> getAllEntreprises();
	public void deleteEntrepriseById(int entrepriseId);

	/*public int ajouterDepartement(Departement dep);
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public void deleteDepartementById(int depId);*/
	
}
