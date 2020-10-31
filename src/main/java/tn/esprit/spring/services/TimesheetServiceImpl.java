package tn.esprit.spring.services;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.log4j.Logger;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class); 
	
	@Override
	public Mission getMissionById(int id) {
		try {
			l.info(" get mission with id" +id);

			return  missionRepository.findById(id).get();
		

			
		} catch (Exception e) {
			l.error("get mission operation failed !!!!");	
		}
		return null;
	}
	
	/*@Override
	public Departement getDepartementById(int id) {
		try {
			l.info(" get departement with id" +id);

			return  deptRepoistory.findById(id).get();
		

			
		} catch (Exception e) {
			l.error("get Departement operation failed !!!!");	
		}
		return null;
	}*/
	
	public Mission ajouterMission(Mission mission) {
		try {
			l.info(" loading ajouter mission");
	Mission m=	missionRepository.save(mission);
		l.info(" mission ajouté avec succés");
	return m;
		} 
		catch (Exception e) {
			l.error("saving not completed !!!!");	
		
		}
		return null;
	}
    
	public Mission affecterMissionADepartement(int missionId, int depId) {
		
		try { 
		
			l.info(" begin affecter Mission a deparement ");
			
		
		Mission mission = missionRepository.findById(missionId).get();
		Departement dep = deptRepoistory.findById(depId).get();
		mission.setDepartement(dep);
		Mission m=	missionRepository.save(mission);
		l.info("affectation complete ...");
		return m;
		
		} catch (Exception e){
			l.error("faild to affecter");
			
		}
		return null;
		
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		try {
			l.info("Begin Ajout Timesheet");
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		l.info("ajout time sheet complete");
		}
		catch (Exception e) {
			l.error("faild  to add timesheet");
			
		}
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		
		try { 
			l.info(" Find all mission by employe ");
		
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	
		} catch (Exception e) { 
			l.error("faild to find mission ");
			
		}
		return null;
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		try { 
			l.info(" Find all employes by mission  ");
		
		return timesheetRepository.getAllEmployeByMission(missionId);
		} catch (Exception e) { 
			l.error("faild to find employees");
			
		}
		return null;
	}
	@Transactional
	public void deleteMissionById(int id) {
		try {
			l.info(" search for Mission");
		Mission d = missionRepository.findById(id).get();
		l.info(" found Mission");
		l.info(" deleting  mission");
		missionRepository.delete(d);	
		l.info(" operation finish  mission");
		} catch (Exception e) {
			l.error("mission could not be found !!!!");	
		}
		
	}
	public List<Mission> getAllMission() {
		l.info(" In get all  mission");
	List<Mission> missions = (List<Mission>) missionRepository.findAll();
	for (Mission mission : missions) {
		l.debug("mission +++ : " + mission.getName());
	}
	l.info("Out of getAllMission."); 
	return missions;
	
}

}
