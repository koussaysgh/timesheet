package tn.esprit.spring.services;

import java.util.List; 

import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public Entreprise ajouterEntreprise(Entreprise entreprise);
	public Entreprise updateEntreprise(Entreprise entreprise);
	public Entreprise getEntrepriseById(int entrepriseId);
	public List<Entreprise> getAllEntreprises();
	public void deleteEntrepriseById(int entrepriseId);


}
