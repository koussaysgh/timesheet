package tn.esprit.spring.services;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import org.apache.log4j.Logger;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class); 
	
	// -------------------------------------------------------------crud
	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		try {
			l.info(" loading save entreprise");
			Entreprise em = entrepriseRepoistory.save(entreprise);
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
			Entreprise em = entrepriseRepoistory.save(entreprise);
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
			Entreprise em = entrepriseRepoistory.findById(entrepriseId).get();
			l.info(" found entreprise");
			l.info(" deleting  entreprise");
		entrepriseRepoistory.delete(em);
		l.info(" operation finish  entreprise");
		} catch (Exception e) {
			l.error("entreprise could not be found !!!!");	
		}
	}
	
	@Override
	public Entreprise getEntrepriseById(int entrepriseId) {
		try {
			l.info(" get entreprise with id = " + entrepriseId);

			    Entreprise e =  entrepriseRepoistory.findById(entrepriseId).get();
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

			List<Entreprise> entreprises =  (List<Entreprise>) entrepriseRepoistory.findAll();
		
			for (Entreprise ent : entreprises) {
				l.debug("entreprise +++ : " + ent);
			}
			
		
			l.info("out of getAllEntreprises ");	
		
		return entreprises;
	}

//----------------------------------------------------------------------
	
	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	
}
