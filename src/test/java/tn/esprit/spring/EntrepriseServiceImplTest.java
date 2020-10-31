package tn.esprit.spring;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)

@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired 
	IEntrepriseService ents;
	private static final Logger l = Logger.getLogger(EntrepriseServiceImplTest.class); 
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Test
	public void testAjouterEntreprise() {
		
		Entreprise ent=new Entreprise(1,"ASUS", "technologies");
		
		Entreprise em= ents.ajouterEntreprise(ent);
		
		assertEquals(em.getName(), em.getName());
		}
	
	@Test

	public void testUpdateEntreprise() {

	Entreprise ent=ents.getEntrepriseById(2);
	l.info("\n"+"----------------------------------------------"+"\n");

	l.info("\n"+ent+"\n");
	
	ent.setName("SAMSUNG");
	Entreprise em= ents.updateEntreprise(ent);
	assertEquals(ent.getName(), em.getName());
		
	}
	
	@Test
        public void testGetAllEntreprises() {
		List<Entreprise> le=ents.getAllEntreprises();
		le.forEach(e->l.info(e+"\n"));
		
		
		}
	@Test
	  public void testGetEntrepriseById(){
		Entreprise ent=ents.getEntrepriseById(2);
		l.info(ent);
		assertEquals(2, ent.getId());
	}
	@Test 
	public void testDeleteEntrepriseById(){
		ents.deleteEntrepriseById(3);
		assertNull(ents.getEntrepriseById(3));
	}

}
