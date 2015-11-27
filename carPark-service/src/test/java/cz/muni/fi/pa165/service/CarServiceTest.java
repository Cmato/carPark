package cz.muni.fi.pa165.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import org.testng.Assert;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarServiceTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private CarDao carDao;

    @Autowired
    @InjectMocks
    private CarService carService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Car testCar;

    
    @BeforeMethod
    public void prepareTestCar(){
    	testCar = new Car();
        testCar.setColor("White");
        testCar.setFuel(Fuel.Diesel);
        testCar.setName("Renault");
        testCar.setTransmission(Transmission.Manual);

        carDao.createCar(testCar);
    }
    
    @Test(expectedExceptions = CarParkServiceException.class)
    public void testCreateCarWithWrongParameters() {
        Car testWrongCar = new Car();
        testWrongCar.setName("Renault");
        
        carService.createCar(testWrongCar);
    }
    
    @Test(expectedExceptions = CarParkServiceException.class)
    public void testRemoveCarWithWrongParameters() {        
        carService.removeCar(null);
    }

    @Test
    public void testGetCarById() {
        Car dbCar = carService.getCarById(testCar.getId());
        
        Assert.assertEquals(dbCar.getId(), testCar.getId());
    }
    
    @Test
    public void testGetCarsByTransmission() {
        List<Car> dbManualCars = carService.getCarsByTransmission(Transmission.Manual);
        List<Car> testManualCars = new ArrayList<>();
        testManualCars.add(testCar);
        Assert.assertEquals(dbManualCars, testManualCars);
    }

    @Test
    public void testGetCarsByFuel() {
        List<Car> dbManualCars = carService.getCarsByFuel(Fuel.Diesel);
        List<Car> testManualCars = new ArrayList<>();
        testManualCars.add(testCar);
        Assert.assertEquals(dbManualCars, testManualCars);
    }

}
