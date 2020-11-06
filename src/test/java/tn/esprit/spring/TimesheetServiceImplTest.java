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
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.ITimesheetService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {
	@Autowired 
	ITimesheetService itimesheet;
	@Autowired 
	IDepartementService ids;
	private static final Logger l = Logger.getLogger(TimesheetServiceImplTest.class); 
	@Test
	public void testAjoutMission()   {

		Mission m=new Mission("ingenieur","ing ");
		Mission mi= itimesheet.ajouterMission(m);
		Assert.assertEquals(m.getName(), mi.getName());
		
		
		
		
	}
	@Test 
	public void testaffecterMissionADepartement()  {

		Mission m=  itimesheet.getMissionById(1);
		Departement dep=ids.getDepartmentById(1);
		m.setDepartement(dep);
		Mission mi = itimesheet.affecterMissionADepartement(1,1);
		Assert.assertEquals(m.getName(), mi.getName());
		
		
	}
	@Test
	  public void testgetMissionById(){
		Mission m=itimesheet.getMissionById(1);
		l.info(m);
		Assert.assertEquals(1, m.getId());
	}
	@Test 
	public void testDeleteMissionById(){
		itimesheet.deleteMissionById(2);
		assertNull(itimesheet.getMissionById(2));
	}
	@Test
	public void testGetAllMission() {
		List<Mission> le=itimesheet.getAllMission();
		le.forEach(e->l.info(e+"\n"));
		//assertEquals(1, le.size());

	}

}
