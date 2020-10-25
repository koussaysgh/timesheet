package tn.esprit.spring;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;


@RunWith(SpringRunner.class)

@SpringBootTest
public class EmployeServiceImpltTest {
	@Autowired 
	IEmployeService ems;
	private static final Logger l = Logger.getLogger(EmployeServiceImpltTest.class); 
	@Autowired
	EmployeRepository employeRepository;
	@Test
	public void testAjoutEmploye() {
		
		Employe e=new Employe(12,"sami", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		Employe em= ems.ajoutEmploye(e);
		Assert.assertEquals(e.getNom(), em.getNom());
		
		
	}


	@Test

	public void testUpdateEmploye() {

	Employe e=ems.getEmployeById(2);
	e.setNom("sami"+" +");
	Employe em= ems.updateEmploye(e);
	Assert.assertEquals(e.getNom(), em.getNom());
		
	}

	
	@Test

	public void testGetAllEmployes() {
		List<Employe> le=ems.getAllEmployes();
		le.forEach(e->l.info(e+"\n"));
		
		

		
		
		
	}
	
	
	
	
	
	
}
