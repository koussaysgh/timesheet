package tn.esprit.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import tn.esprit.spring.config.LoginFilter;
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
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
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
	public static void main(String[] args) throws ParseException {
		
		SpringApplication.run(TimesheetApplication.class, args);
	
		
	
	
	
	
	}
	@Override
	public void run(String... args) throws Exception {
		String sDate1="31/12/1998";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		Contrat c=new Contrat( date1,"MENSUEL",2550);
		Contrat c1=new Contrat( date1,"MENSUEL",20);
		Contrat c2=new Contrat( date1,"MENSUEL",3000);
		Contrat co= ics.save(c);
		Contrat co1= ics.save(c1);
		Contrat co2= ics.save(c2);
		
		Departement dep=new Departement(1,"dep2");
		Departement dep1=new Departement(2,"dep3");
		Departement dep2=new Departement(3,"dep4");
		Departement d= ids.save(dep);
		Departement d1= ids.save(dep1);
		Departement d2= ids.save(dep2);
		
		Employe e=new Employe(1,"sami", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		Employe e1=new Employe(2,"salah", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		Employe e2=new Employe(3,"samir", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		Employe em= ems.save(e);
		Employe em1= ems.save(e1);
		Employe em2= ems.save(e2);

		Entreprise ent=new Entreprise(1,"alibaba", "technologies");
		Entreprise ent1=new Entreprise(2,"Amazon", "technologies");
		Entreprise ent2=new Entreprise(3,"carrefour", "technologies");
		Entreprise emt= ents.save(ent);
		Entreprise emt1= ents.save(ent1);
		Entreprise emt2= ents.save(ent2);

		Mission m=new Mission("ingenieur","ing ");
		Mission m1=new Mission("ouvrier","ouv ");
		Mission m2=new Mission("technicien","tec ");
		
		Mission mi= itimesheet.ajouterMission(m);
		Mission mi1= itimesheet.ajouterMission(m1);
		Mission mi2= itimesheet.ajouterMission(m2);
		
	
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf"); }

	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}


	@Bean
	public FilterRegistrationBean loginFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.addUrlPatterns("/pages/*");
		registration.setFilter(new LoginFilter());
		return registration;
	}
 
}
