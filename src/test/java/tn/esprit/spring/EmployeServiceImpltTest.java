package tn.esprit.spring;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
	
	///get employe par id
	// delete
	
	@Test
	public void testAjoutEmploye() {
		
		Employe e=new Employe(1,"ali", "samsar", "sami@gmail.com", true, Role.ADMINISTRATEUR);
		
		Employe em= ems.ajoutEmploye(e);
	Assert.assertEquals(e.getNom(), em.getNom());
		
		
	}


	@Test
	public void testUpdateEmploye() {
	Employe e=ems.getEmployeById(1);
	e.setNom("sami"+" +");
	Employe em= ems.updateEmploye(e);
	Assert.assertEquals(e.getNom(), em.getNom());
	}

	
	
	@Test

	public void testGetAllEmployes() {
		List<Employe> le=ems.getAllEmployes();
		le.forEach(e->l.info(e+"\n"));
		
	
		
		
		
	}
	
	@Test
	  public void testGetEmployeById(){
		Employe e=ems.getEmployeById(1);
		l.info(e);
		assertEquals(1L, e.getId());
	}
	@Test 
	public void testDeleteEmployeById(){
		ems.deleteEmployeById(2);
		assertNull(ems.getEmployeById(2));
	}
	
	
	
	
}
