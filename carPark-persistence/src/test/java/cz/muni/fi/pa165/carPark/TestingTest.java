package cz.muni.fi.pa165.carPark;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

//import cz.muni.fi.pa165.carPark.PersistenceSampleApplicationContext;



@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class TestingTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Test
	public void createFindDeleteTest(){
            Employee empl = new Employee();
            empl.setName("John Snow");
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.YEAR, 2011);
            cal1.set(Calendar.MONTH, 0);
            cal1.set(Calendar.DAY_OF_MONTH, 20);
            empl.setBirth(cal1.getTime());
            employeeDao.createEmployee(empl);
		
		/*Assert.assertEquals(productDao.findById(p.getId()).getName(),"TestProduct1");
		Assert.assertEquals(productDao.findAll().size(), 2);
		Assert.assertEquals(productDao.findByName("TestProduct1").size(), 1);
		productDao.remove(p2);
		Assert.assertEquals(productDao.findAll().size(), 1);*/
		//Solution end
	}
}
