package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	
	@Autowired 
	IContratService ics;
	private static final Logger l = Logger.getLogger(ContratServiceImplTest.class); 
	
	@Test
	public void testAjoutContrat() throws ParseException {
		
		String sDate1="31/12/1998";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		Contrat c=new Contrat( 4,date1,"MENSUEL",999);
		Contrat co= ics.ajouterContrat(c);
		

		assertEquals(co.getReference(),c.getReference());

		
	}
	
	@Test
	public void testUpdateContrat() {
	Contrat c=ics.getContratByRef(1);
	c.setSalaire(200);
	Contrat co= ics.updateContrat(c);
	

	assertEquals(co.getReference(),c.getReference());
	}

	
	@Test
	public void testGetAllContrats() {
		List<Contrat> le=ics.getAllContrats();
		le.forEach(e->l.info(e+"\n"));
		

	}
	
	@Test 
	public void testDeleteContratByRef(){
		ics.deleteContratByRef(2);
		assertNull(ics.getContratByRef(2));
	}
	
	@Test
	public void testGetContratByRef() {
		Contrat c=ics.getContratByRef(3);

		assertEquals(3, c.getReference());

		


	}
	
}
