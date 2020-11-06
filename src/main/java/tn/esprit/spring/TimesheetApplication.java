package tn.esprit.spring;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.TimesheetServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
public class TimesheetApplication implements CommandLineRunner {

	@Autowired
	 
	ContratRepository ics;
	@Autowired
	 
	DepartementRepository ids;
	@Autowired
	 
	EmployeRepository ems;
	@Autowired
	 
	EntrepriseRepository ents;
	@Autowired
	 
	TimesheetServiceImpl itimesheet;
	public static void main(String[] args) 
 {
		
		SpringApplication.run(TimesheetApplication.class, args);
	
		
	
	
	
	
	}
	@Override
	public void run(String... args) throws Exception {
		String sDate1="31/12/1998";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		String a = "MENSUEL" ;
		Contrat c=new Contrat( date1,a,2550);
		Contrat c1=new Contrat( date1,a,20);
		Contrat c2=new Contrat( date1,a,3000);
		 ics.save(c);
		 ics.save(c1);
		 ics.save(c2);
		Departement dep=new Departement(1,"dep2");
		Departement dep1=new Departement(2,"dep3");
		Departement dep2=new Departement(3,"dep4");
		ids.save(dep);
		ids.save(dep1);
		ids.save(dep2);
		
		Employe e=new Employe(1,"sami", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		Employe e1=new Employe(2,"salah", "samsare", "samii@gmail.com", true, Role.ADMINISTRATEUR);
		Employe e2=new Employe(3,"samir", "samsari", "samiii@gmail.com", true, Role.ADMINISTRATEUR);
		ems.save(e);
		ems.save(e1);
		ems.save(e2);
        
		String b = "technologies" ;
		Entreprise ent=new Entreprise(1,"alibaba", b);
		Entreprise ent1=new Entreprise(2,"Amazon", b);
		Entreprise ent2=new Entreprise(3,"carrefour", b);
		ents.save(ent);
		ents.save(ent1);
		ents.save(ent2);

		Mission m=new Mission("ingenieur","ing ");
		Mission m1=new Mission("ouvrier","ouv ");
		Mission m2=new Mission("technicien","tec ");
		
		itimesheet.ajouterMission(m);
		itimesheet.ajouterMission(m1);
		itimesheet.ajouterMission(m2);
		
	
	}

	


	
 
}
