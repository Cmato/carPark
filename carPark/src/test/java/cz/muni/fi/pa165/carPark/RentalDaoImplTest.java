package cz.muni.fi.pa165.carPark;

import static org.testng.AssertJUnit.*;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.TransactionSystemException;
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
    
    @Test
    @DirtiesContext
    public void removeRentalTest(){
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29)); 
    	Car c2 = TestHelper.car("Skoda Fabia","Red",Fuel.Diesel,Transmission.manual);
    	Employee e2 = TestHelper.employee("Pepa2", DateFormater.newDate(2000, 12, 1), "ABC124");
    	Rental rent2 = TestHelper.rental(e, c, DateFormater.newDate(2015, 11, 29), DateFormater.newDate(2015, 12, 29)); 
    	
    	cDAO.createCar(c);
    	eDAO.createEmployee(e);
    	cDAO.createCar(c2);
    	eDAO.createEmployee(e2);
    	

    	DAO.create(rent);
    	DAO.create(rent2);

    	DAO.remove(rent); // remove 
    	
    	assert(DAO.findAll().size() == 1); //check
    	assertEquals(DAO.findByCar(c).get(0), rent2);

    	rent.setId(null); // reset index
    	DAO.create(rent); //imput same thing again
    	assert(DAO.findAll().size() == 2);
    	
    	
    }
    
    @Test
    @DirtiesContext
    public void removeWithNullRentalTest(){
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29)); 
    	
    	cDAO.createCar(c);
    	eDAO.createEmployee(e);   	

    	DAO.create(rent);
    	
    	// try to remove null
    	try {
    		DAO.remove(null);
            fail("Ex not thrown");
        } catch (IllegalArgumentException ex) {
        }
    	
    	rent.setCar(null);
    	rent.setEmployee(null);
    	rent.setId(null);
    	
    	// try to remove corupted rental
    	try {
    		DAO.remove(null);
            fail("Ex not thrown");
        } catch (IllegalArgumentException ex) {
        }
    	assert(DAO.findAll().size() == 1);
    
    }
    
    @Test
    @DirtiesContext
    public void updateRentalTest(){
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Car c2 = TestHelper.car("Skoda Fabia","Red",Fuel.Diesel,Transmission.manual);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29));
    	
    	cDAO.createCar(c);
    	cDAO.createCar(c2);
    	eDAO.createEmployee(e);
    	
    	DAO.create(rent);
    	
    	rent.setStartingDate(DateFormater.newDate(2014, 10, 29));
    	rent.setEndingDate(DateFormater.newDate(1900, 11, 29));
    	rent.setCar(c2);
    	
    	DAO.update(rent);
    	assert(DAO.findByCar(c2).size() == 1);    	
    	Rental rent2 = DAO.findByCar(c2).get(0);
    	assertEquals(rent2.getStartingDate(), DateFormater.newDate(2014, 10, 29));
    	assertEquals(rent2.getCar(), c2);
    }
    
    @Test
    @DirtiesContext
    public void updateWithNullRentalTest(){
    	Car c = TestHelper.car("Skoda Superb","Black",Fuel.Petrol,Transmission.Automatic);
    	Employee e = TestHelper.employee("Pepa", DateFormater.newDate(2000, 12, 1), "ABC123");
    	Rental rent = TestHelper.rental(e, c, DateFormater.newDate(2015, 10, 29), DateFormater.newDate(2015, 11, 29));
    	
    	cDAO.createCar(c);
    	eDAO.createEmployee(e);
    	
    	DAO.create(rent);
    	
    	rent.setStartingDate(null);
    	rent.setEndingDate(null);
    	rent.setCar(null);
    	
    	try{
    		DAO.update(rent);
    		fail("Ex not thrown");
    	} catch (TransactionSystemException ex) {
    		
    	}  
    }
    
    @Test
    public void findNonExistObject(){
        assertNull((Object)DAO.findById(321231231l));
    }
    
    
    
}

