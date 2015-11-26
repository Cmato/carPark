package cz.muni.fi.pa165;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;

/**
 *
 * @author xhasprun
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class CarDaoTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    CarDao carDao;
    
    @Test
    public void createCarDaoTest(){
        
        Car testCar = new Car();
        testCar.setColor("Black");
        testCar.setFuel(Fuel.Diesel);
        testCar.setName("Renault");
        testCar.setTransmission(Transmission.Manual);
        
        carDao.createCar(testCar);
        Car sameCar = carDao.findCarById(testCar.getId());
        assert(testCar.equals(sameCar));
        
    }
    
    @Test
    public void deleteCarDaoTest(){
        
        Car testCar = new Car();
        testCar.setColor("Black");
        testCar.setFuel(Fuel.Diesel);
        testCar.setName("Renault");
        testCar.setTransmission(Transmission.Manual);
        
        carDao.createCar(testCar);
        carDao.deleteCar(testCar);
        carDao.findCarById(testCar.getId());
        Car sameCar = carDao.findCarById(testCar.getId());
        assert(!testCar.equals(sameCar) && sameCar==null);
    }
    
    @Test
    public void updateCarTest(){
        Car testCar = new Car();
        testCar.setColor("Red");
        testCar.setFuel(Fuel.CNG);
        testCar.setName("Ferrari");
        testCar.setTransmission(Transmission.Automatic);
        
        carDao.createCar(testCar);
        
        testCar.setColor("Blue");
        testCar.setFuel(Fuel.Petrol);
        testCar.setName("Skoda");
        testCar.setTransmission(Transmission.Manual);
        
        carDao.updateCar(testCar);
        
        Car sameCar = carDao.findCarById(testCar.getId());
        assert(testCar.equals(sameCar));
    }
    
    @Test
    public void equalsTest(){
        Car testCar = new Car();
        testCar.setColor("Red");
        testCar.setFuel(Fuel.CNG);
        testCar.setName("Ferrari");
        testCar.setTransmission(Transmission.Automatic);
        
        carDao.createCar(testCar);
        
        testCar.setColor("Blue");
        testCar.setFuel(Fuel.Petrol);
        testCar.setName("Skoda");
        testCar.setTransmission(Transmission.Manual);
        
        carDao.updateCar(testCar);
        
        Car sameCar = carDao.findCarById(testCar.getId());
        
        
        assert(testCar.equals(sameCar));
        assert(testCar.getColor().equals(sameCar.getColor()));
        assert(testCar.getFuel() == sameCar.getFuel());
        assert(testCar.getId()== sameCar.getId());
        assert(testCar.getName().equals(sameCar.getName()));
        assert(testCar.getTransmission() == sameCar.getTransmission());
        
    }
    
    
    
    
}
