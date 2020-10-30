package tn.esprit.spring;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Mission;


import tn.esprit.spring.services.ITimesheetService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {
	@Autowired 
	ITimesheetService itimesheet;
	private static final Logger l = Logger.getLogger(TimesheetServiceImplTest.class); 
	@Test
	public void testAjoutMission() throws ParseException {

		Mission m=new Mission("ingenieur","ing ");
		Mission mi= itimesheet.ajouterMission(m);
		Assert.assertEquals(m.getName(), mi.getName());
		
		
		
		
	}
	

}
