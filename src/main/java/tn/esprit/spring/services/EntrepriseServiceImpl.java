package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.log4j.Logger;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepository;
	

	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class); 
	
	// -------------------------------------------------------------crud
	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		try {
			l.info(" loading save entreprise");
			Entreprise em = entrepriseRepository.save(entreprise);
			l.info(" Successful saving entreprise");
			return em;
		} catch (Exception e) {
           l.error("saving not completed !!!!");	
          }return null;
	      }
	
	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		try {
			l.info(" loading update entreprise");
			Entreprise em = entrepriseRepository.save(entreprise);
			l.info(" Successful update entreprise");
			return em;
		} catch (Exception e) { 
         l.error("update not completed !!!!");	
        }
		
		return null;
	}
	
	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		try {
			l.info(" search for entreprise");
			Entreprise em = entrepriseRepository.findById(entrepriseId).get();
			l.info(" found entreprise");
			l.info(" deleting  entreprise");
		entrepriseRepository.delete(em);
		l.info(" operation finish  entreprise");
		} catch (Exception e) {
			l.error("entreprise could not be found !!!!");	
		}
	}
	
	@Override
	public Entreprise getEntrepriseById(int entrepriseId) {
		try {
			l.info(" get entreprise with id = " + entrepriseId);
			    Entreprise e =  entrepriseRepository.findById(entrepriseId).get();
				l.info("entreprise returned : " + e);
                return e ;

			
		} catch (Exception e) {
			l.error("get entreprise operation failed !!!!");	
		}
		return null;
	}
	
	@Override
	public List<Entreprise> getAllEntreprises() {
		
			l.info(" In getAllEntreprises");

			List<Entreprise> entreprises =  (List<Entreprise>) entrepriseRepository.findAll();
		
			for (Entreprise ent : entreprises) {
				l.debug("entreprise +++ : " + ent);
			}
			
		
			l.info("out of getAllEntreprises ");	
		
		return entreprises;
	}

//----------------------------------------------------------------------
	



	
}
