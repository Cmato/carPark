package cz.muni.fi.pa165;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.ArrayList;
import java.util.List;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author xhasprun
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CarDaoTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    CarDao carDao;

    private Car passat;
    
    private Car cla;

    @BeforeMethod
    public void setUpClass() {
        passat = new Car("VW Passat", "Black", Fuel.Diesel, Transmission.Manual);
        cla = new Car("MB CLA", "White peral", Fuel.Diesel, Transmission.Automatic);
        
        carDao.createCar(passat);
        carDao.createCar(cla);
    }

    /**
     * Test of findCarById method, of CarDao class.
     */ 
    @Test
    @Transactional
    public void testFindCarById() {
        Car foundCar = carDao.findCarById(passat.getId());
        Assert.assertEquals(passat, foundCar);
    }

    /**
     * Test of findAllCars method, of CarDao class.
     */
    @Test
    @Transactional
    public void testFindAllCars() {
        List<Car> foundCars = carDao.findAllCars();
       
        List<Car> expectedResult = new ArrayList();
        expectedResult.add(passat);
        expectedResult.add(cla);
        
        Assert.assertEquals(foundCars.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    /**
     * Test of findCarByFuel method, of CarDao class.
     */
    @Test
    @Transactional
    public void testFindCarByFuel() {
        List<Car> foundCars = carDao.findCarByFuel(passat.getFuel());
       
        List<Car> expectedResult = new ArrayList();
        expectedResult.add(passat);
        expectedResult.add(cla);
        
        Assert.assertEquals(foundCars.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    /**
     * Test of findCarByTransmission method, of CarDao class.
     */
    @Test
    @Transactional
    public void testFindCarByTransmission() {
        List<Car> foundCars = carDao.findCarByTransmission(passat.getTransmission());
       
        List<Car> expectedResult = new ArrayList();
        expectedResult.add(passat);
        
        Assert.assertEquals(foundCars.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    /**
     * Test of createCar method, of CarDao class.
     */  
    @Test
    @Transactional
    public void testCreateCar() {
        //if create method works, there have to be passat already created
        Car sameCar = carDao.findCarById(passat.getId());
        Assert.assertEquals(passat, sameCar);
    }

    /**
     * Test of deleteCar method, of CarDao class.
     */  
    @Test
    public void TestDeleteCarDao(){
        carDao.deleteCar(cla);
        Car sameCar = carDao.findCarById(cla.getId());
        assert(!cla.equals(sameCar) && sameCar==null);
    }
    
    /**
     * Test of updateCar method, of CarDao class.
     */ 
    @Test
    public void TestUpdateCar() {
        passat.setColor("Blue");
        passat.setFuel(Fuel.Petrol);
        passat.setName("VW Passat variant");
        passat.setTransmission(Transmission.Manual);
        
        carDao.updateCar(passat);

        Car verifyCar = carDao.findCarById(passat.getId());

        Assert.assertEquals(passat, verifyCar);
    }    
}
