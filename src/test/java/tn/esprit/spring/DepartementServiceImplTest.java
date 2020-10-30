package tn.esprit.spring;



import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IDepartementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	@Autowired 
	IDepartementService ids;
	private static final Logger l = Logger.getLogger(DepartementServiceImplTest.class); 
	@Autowired
	DepartementRepository departementRepository;
	@Test
	public void testAjouterDepartement() {
		
		Departement dep=new Departement(1,"dep1");
		Departement d= ids.ajouterDepartment(dep);
		equals(d.getName());
		}
	
	@Test

	public void testUpdateDepartement() {

	Departement dep=ids.getDepartmentById(5);
	dep.setName("dep4");
	Departement d= ids.updateDepartment(dep);
	assertEquals(dep.getName(), d.getName());
		
	}
	
	@Test
        public void testGetAllDepartements() {
		List<Departement> dep=ids.getAllDepartements();
		dep.forEach(e->l.info(e+"\n"));
		assertEquals(3, dep.size());
		
		}
	@Test
	  public void testGetDepartementById(){
		Departement dep=ids.getDepartmentById(5);
		l.info(dep);
		assertEquals(5L, dep.getId());
	}
	@Test 
	public void testDeleteDepartementById(){
		ids.deleteDepartmentById(6);
		assertNull(ids.getDepartmentById(6));
	}
	
	

	

}
