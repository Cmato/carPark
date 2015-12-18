package cz.muni.fi.pa165;

import static org.testng.AssertJUnit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.ArrayList;
import java.util.List;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
*
* @author xruzic16
*/

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RentalDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeDao eDAO;

    private Employee ignac;
    private Employee stefan;
    
    @Autowired
    private CarDao cDAO;

    private Car passat;
    private Car cla;

    @Autowired
    private RentalDao DAO;
    
    private Rental rentalOne;
    private Rental rentalTwo;

    @BeforeMethod
    public void setUpClass() {
        ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456", "email@gmail.com", "123456");
        stefan = new Employee("Stefan", DateFormater.newDate(1998, 11, 11), "BB654321", "email1@gmail.com", "654321");
        
        //ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456");
        //stefan = new Employee("Stefan", DateFormater.newDate(1998, 11, 11), "BB654321");
        
        
        eDAO.createEmployee(ignac);
        eDAO.createEmployee(stefan);

        passat = new Car("VW Passat", "Black", Fuel.Diesel, Transmission.Manual);
        cla = new Car("MB CLA", "White peral", Fuel.Diesel, Transmission.Automatic);
        
        cDAO.createCar(passat);
        cDAO.createCar(cla);

        rentalOne = new Rental(ignac, passat, DateFormater.newDate(2015, 11, 1), DateFormater.newDate(2015, 11, 5));
        rentalTwo = new Rental(stefan, cla, DateFormater.newDate(2015, 11, 2), DateFormater.newDate(2015, 11, 10));

        DAO.create(rentalOne);
        DAO.create(rentalTwo);
    }

    /**
     * Test of findById method, of RentalDao class.
     */ 
    @Test
    @Transactional
    public void testFindById() {
        Rental foundRental = DAO.findById(rentalOne.getId());
        Assert.assertEquals(rentalOne, foundRental);
    }

    /**
     * Test of findAll method, of RentalDao class.
     */
    @Test
    @Transactional
    public void testFindAll() {
        List<Rental> foundRentals = DAO.findAll();
       
        List<Rental> expectedResult = new ArrayList();
        expectedResult.add(rentalOne);
        expectedResult.add(rentalTwo);
        
        Assert.assertEquals(foundRentals.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundRentals.get(i));
        }
    }

    /**
     * Test of findByEmployee method, of RentalDao class.
     */
    @Test
    @Transactional
    public void testFindByEmployee() {
        List<Rental> foundRentals = DAO.findByEmployee(ignac);
       
        List<Rental> expectedResult = new ArrayList();
        expectedResult.add(rentalOne);
        
        Assert.assertEquals(foundRentals.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundRentals.get(i));
        }
    }

    /**
     * Test of findByCar method, of RentalDao class.
     */
    @Test
    @Transactional
    public void testFindByCar() {
        List<Rental> foundRentals = DAO.findByCar(passat);
       
        List<Rental> expectedResult = new ArrayList();
        expectedResult.add(rentalOne);
        
        Assert.assertEquals(foundRentals.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundRentals.get(i));
        }
    }

    /**
     * Test of findRentalsWithState method, of RentalDao class.
     */
    @Test
    @Transactional
    public void testFindRentalsWithState() {
        List<Rental> foundRentals = DAO.findRentalsWithState(RentalState.ACTIVE);
       
        List<Rental> expectedResult = new ArrayList();
        expectedResult.add(rentalOne);
        expectedResult.add(rentalTwo);
        
        Assert.assertEquals(foundRentals.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundRentals.get(i));
        }
    }

    /**
     * Test of create method, of RentalDao class.
     */
    @Test
    @Transactional
    public void testCreateRental() {    
        //if create method works, there have to be ignac already created    
        Rental sameRent = DAO.findById(rentalOne.getId());

        assertEquals(rentalOne, sameRent);   
    }

    /**
     * Test of create method, of RentalDao class with null parameters.
     */
    @Test
    @Transactional
    public void testCreateRentalNull() {
    	Rental rent = new Rental(null,null,null,null);
    	
    	try {
    		DAO.create(null);
            fail("Ex not thrown");
        } catch (NullPointerException ex) {
        }
    	
    	try {
    		DAO.create(rent);
            fail("Ex not thrown");
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of remove method, of RentalDao class.
     */  
    @Test
    @Transactional
    public void testRemoveRental() {
        DAO.remove(rentalTwo);
        Rental sameRental = DAO.findById(rentalTwo.getId());
        assert(!rentalTwo.equals(sameRental) && sameRental == null);
    }

    
    /**
     * Test of remove method, of RentalDao class with wrong parameters.
     */  
    @Test
    @Transactional
    public void testRemoveWithNullRental() {    	
    	// try to remove null
    	try {
    		DAO.remove(null);
            fail("Ex not thrown");
        } catch (NullPointerException ex) {
        }
    	
    	rentalOne.setCar(null);
    	
    	// try to remove corupted rental
    	try {
    		DAO.remove(rentalOne);
            fail("Ex not thrown");
        } catch (IllegalArgumentException ex) {
            rentalOne.setCar(passat);
        }

    	assert(DAO.findAll().size() == 2);
    }
    
    /**
     * Test of update method, of RentalDao class.
     */  
    @Test
    @Transactional
    public void testUpdateRental() {
        Car bmw = new Car("BMW M3", "Blue", Fuel.Petrol, Transmission.Automatic);
        cDAO.createCar(bmw);
        
    	rentalOne.setStartingDate(DateFormater.newDate(2014, 10, 29));
    	rentalOne.setEstimatedReturnDate(DateFormater.newDate(2015, 11, 29));
    	rentalOne.setCar(bmw);
    	
    	DAO.update(rentalOne);
    	assert(DAO.findByCar(bmw).size() == 1);    	
    	Rental rent2 = DAO.findByCar(bmw).get(0);
    	assertEquals(rent2.getStartingDate(), DateFormater.newDate(2014, 10, 29));
    	assertEquals(rent2.getCar(), bmw);
    }
    
    /**
     * Test of update method, of RentalDao class with wrong parameters.
     */  
    @Test
    @Transactional
    public void updateWithNullRentalTest() {
    	rentalOne.setCar(null);
    	
    	try{
    		DAO.update(rentalOne);
    		fail("Ex not thrown");
    	} catch (IllegalArgumentException ex) {
    		rentalOne.setCar(passat);
    	}  
    }    
}

