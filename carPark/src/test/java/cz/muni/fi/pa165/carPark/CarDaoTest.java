package cz.muni.fi.pa165.carPark;

import cz.muni.fi.pa165.carPark.daos.CarDao;
import cz.muni.fi.pa165.carPark.daos.EmployeeDao;
import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.enums.Fuel;
import cz.muni.fi.pa165.carPark.enums.Transmission;
import java.util.Calendar;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        testCar.setTransmission(Transmission.manual);
        
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
        testCar.setTransmission(Transmission.manual);
        
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
        testCar.setTransmission(Transmission.manual);
        
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
        testCar.setTransmission(Transmission.manual);
        
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
