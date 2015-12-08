package cz.muni.fi.pa165.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;

/**
 *
 * @author xcmarko
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class CarServiceTest extends AbstractTransactionalTestNGSpringContextTests  {
    
    @Mock
    private CarDao carDao;
    
    @Autowired
    @InjectMocks
    private CarService carService;
    
    private Car bmw;
    private Car bmwCopy;
    private Car cla;
    private Car passat;
    private Car octavia;

    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        bmw = new Car("BMW 3M", "M Blue", Fuel.Petrol, Transmission.Manual);
        bmwCopy = bmw;
        cla = new Car("MB CLA AMG", "White", Fuel.Petrol, Transmission.Manual);
        passat = new Car("VW Passat", "Black", Fuel.Diesel, Transmission.Automatic);
        octavia = new Car("Skoda Octavia", "Blue", Fuel.Diesel, Transmission.Automatic);
    }
    
    @Test
    public void testCreateCar() {        
        when(carDao.createCar(any(Car.class))).thenReturn(true);
        Car createdCar = carService.createCar(bmw);       
        assertEquals(bmw, createdCar);
    }

    @Test
    public void testCreateExistingCar() {
        when(carDao.createCar(any(Car.class))).thenReturn(false);
        Car createdCar = carService.createCar(bmw);  
        assertEquals(null, createdCar);
    }

    @Test
    public void testDeleteCar() {        
        when(carDao.deleteCar(any(Car.class))).thenReturn(true);
        boolean result = carService.deleteCar(cla);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteNonExistingCar() {        
        when(carDao.deleteCar(any(Car.class))).thenReturn(false);
        boolean result = carService.deleteCar(cla);
        assertEquals(false, result);
    }

    @Test
    public void testUpdateName() {        
        String expectedNewName = "BMW 5M";
        bmwCopy.setName(expectedNewName);
        when(carDao.updateCar(bmw)).thenReturn(bmwCopy);
        Car updatedResult = carService.updateName(bmw, expectedNewName);
        assertEquals(expectedNewName, updatedResult.getName());
    }

    @Test
    public void testUpdateColor() {        
        String expectedColor = "M Gold";
        bmwCopy.setColor(expectedColor);
        when(carDao.updateCar(bmw)).thenReturn(bmwCopy);
        Car updatedResult = carService.updateColor(bmw, expectedColor);
        assertEquals(expectedColor, updatedResult.getColor());
    }

    @Test
    public void testUpdateFuel() {        
        Fuel expectedFuel = Fuel.Diesel;
        bmwCopy.setFuel(expectedFuel);
        when(carDao.updateCar(bmw)).thenReturn(bmwCopy);
        Car updatedResult = carService.updateFuel(bmw, expectedFuel);
        assertEquals(expectedFuel, updatedResult.getFuel());
    }

    @Test
    public void testUpdateTransmission() {        
        Transmission expectedTransmission = Transmission.Automatic;
        bmwCopy.setTransmission(expectedTransmission);
        when(carDao.updateCar(bmw)).thenReturn(bmwCopy);
        Car updatedResult = carService.updateTransmission(bmw, expectedTransmission);
        assertEquals(expectedTransmission, updatedResult.getTransmission());
    }

    @Test
    public void testGetCarById() {      
        when(carDao.findCarById(any(Long.class))).thenReturn(bmwCopy);
        Car result = carService.getCarById(1l);
        assertEquals(bmwCopy, result);
    }

    @Test
    public void testGetCarsByFuel() {
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);     
        when(carDao.findCarByFuel(Fuel.Diesel)).thenReturn(expectedResult);
        List<Car> foundCars = carService.getCarsByFuel(Fuel.Diesel);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    @Test
    public void testGetCarsByTransmission() {
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);     
        when(carDao.findCarByTransmission(Transmission.Automatic)).thenReturn(expectedResult);
        List<Car> foundCars = carService.getCarsByTransmission(Transmission.Automatic);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    @Test
    public void testFindAll() {
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.bmw);
        expectedResult.add(this.cla);
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);
        when(carDao.findAllCars()).thenReturn(expectedResult);
        List<Car> foundCars = carService.getAllCars();
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }

    @Test
    public void testGetCarsByTransmissionAndFuel() {        
        List<Car> allCarsWithAutomaticTrans = new ArrayList<>();
        allCarsWithAutomaticTrans.add(this.passat);
        allCarsWithAutomaticTrans.add(this.octavia);
        List<Car> allCarsWithDieselFuel = new ArrayList<>();
        allCarsWithDieselFuel.add(this.passat);
        allCarsWithDieselFuel.add(this.octavia);
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);

        when(carDao.findCarByFuel(Fuel.Diesel)).thenReturn(allCarsWithDieselFuel);
        when(carDao.findCarByTransmission(Transmission.Automatic)).thenReturn(allCarsWithAutomaticTrans);
        List<Car> foundCars = carService.getCarsByTransmissionAndFuel(Transmission.Automatic, Fuel.Diesel);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundCars.get(i));
        }
    }
}
