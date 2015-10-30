package cz.muni.fi.pa165.carPark;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.fail;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.carPark.daos.CarDao;
import cz.muni.fi.pa165.carPark.daos.EmployeeDao;
import cz.muni.fi.pa165.carPark.daos.RentalDao;
import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.entities.Rental;
import cz.muni.fi.pa165.carPark.enums.Fuel;
import cz.muni.fi.pa165.carPark.enums.Transmission;
import cz.muni.fi.pa165.carPark.utils.DateFormater;

/**
*
* @author xruzic16
*/

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class RentalDaoImplTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private RentalDao DAO;
	@Autowired
	private CarDao cDAO;
	@Autowired
	private EmployeeDao eDAO;
	
    @BeforeTest
    public void before(){
    	
    }

    @AfterTest
    public void close() {
    	
    }
    
      
    @Test
    @DirtiesContext
    public void createRentalTest(){
    	
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29));
    	
    	//assertNotNull(rent.getId()); // blbost to testovat - priradi se az pri commitu do DB
    	assertNotNull(rent.getEmployee());
    	assertNotNull(rent.getCar());
    	assertNotNull(rent.getStartingDate());
    	assertNotNull(rent.getEndingDate());
    	
    	cDAO.createCar(c);
    	eDAO.createEmployee(e);
    	
    	DAO.create(rent);
    	
    	Rental sameRent = DAO.findByCar(c).get(0);
    	
    	assertNotNull(sameRent.getId());
    	
    	assertEquals(rent, sameRent);
    	assertNotSame(rent, sameRent);    
    	
    }
    
    @Test
    @DirtiesContext
    public void createRentalNullTest(){
    	Rental rent = TestHelper.rental(null,null,null,null);
    	
    	
    	try {
    		DAO.create(null);
            fail("Ex not thrown");
        } catch (IllegalArgumentException ex) {
        }
    	
    	try {
    		DAO.create(rent);
            fail("Ex not thrown");
        } catch (ConstraintViolationException ex) {
        }
    }
//<<<<<<< HEAD
//}
//=======
    
    @Test
    @DirtiesContext
    public void removeRentalTest(){
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29)); 
    	Car c2 = TestHelper.car("Skoda Superb2","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e2 = TestHelper.employee("Pepa2", DateFormater.newDate(2000, 12, 1), "ABC124");
    	Rental rent2 = TestHelper.rental(e, c, DateFormater.newDate(2015, 11, 29), DateFormater.newDate(2015, 12, 29)); 
    	
    	cDAO.createCar(c);
    	eDAO.createEmployee(e);
    	cDAO.createCar(c2);
    	eDAO.createEmployee(e2);
    	

    	DAO.create(rent);
    	DAO.create(rent2);

    	DAO.remove(rent);
    	
    	assert(DAO.findAll().size() == 1);
    	assertEquals(DAO.findByCar(c).get(0), rent2);

    	rent.setId(null);
    	DAO.create(rent);
    	assert(DAO.findAll().size() == 2);
    }
    
    
}
//>>>>>>> 85f51071b75c38952615c1d15fde393f1350814e
