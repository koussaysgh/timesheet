package tn.esprit.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@Service
public class ContratServiceImpl implements IContratService {
	private static final Logger l = Logger.getLogger(ContratServiceImpl.class); 


	@Autowired
	ContratRepository contratRepository;

	@Autowired
	EmployeRepository employeRepository;
	
	public List<Contrat> getAllContrats() {
			l.info(" In get all  contrats");
		List<Contrat> contrats = (List<Contrat>) contratRepository.findAll();
		for (Contrat contrat : contrats) {
			l.debug("contrat +++ : " + contrat);
		}
		l.info("Out of getAllContrats."); 
		return contrats;
		
	}


	@Override
	public Contrat ajouterContrat(Contrat contrat) {
		try {
		l.info(" loading save contrat");
		Contrat c = contratRepository.save(contrat);
		l.info(" Successful saving contrat");
		return c;
		} catch (Exception e) {
	           l.error("saving not completed !!!!");	
	          }return null;
	}

	

	@Transactional
	public void deleteContratByRef(int contratRef) {
		try {
			l.info(" search for contrat");
			if (contratRepository.findById(contratRef).isPresent()) {
		Contrat c = contratRepository.findById(contratRef).get();
		
		l.info(" found contrat");
		l.info(" deleting  contrat");
		contratRepository.delete(c);	
		l.info(" operation finish  contrat");
			}
		} catch (Exception e) {
			l.error("contrat could not be found !!!!");	
		}
	}

	


	@Override
	public Contrat getContratByRef(int contratRef) {
		try {
			l.info(" get contrat");
		return  contratRepository.findById(contratRef).get();
		} catch (Exception e) {
			l.error("get contrat operation failed");	
		}
		return null;
	}


	@Override
	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepository.findById(contratId).get();


		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepository.save(contratManagedEntity);		
	}


	@Override
	public Contrat updateContrat(Contrat contrat) {
		try {
			l.info(" loading update contrat");
			Contrat c = contratRepository.save(contrat);
			l.info(" Successful update employe");
			return c;
		} catch (Exception e) {
l.error("update not completed !!!!");	
}
		
		return null;
	}
	
	@Override
	public Contrat ajoutContrat(Contrat contrat) {
		try {
		return contratRepository.save(contrat);
		} catch (Exception e) {
	           l.error("saving not completed !!!!");	
	          }return null;
	}

}
