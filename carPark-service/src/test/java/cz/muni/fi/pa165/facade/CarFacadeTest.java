package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
public class CarFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CarDao carDao;
    
    @Autowired
    private CarFacade carFacade;
    
    @Autowired
    @InjectMocks
    private CarService carService;
    
    @Autowired
    private MappingService mappingService;

    private Car bmw;
    private CarDTO bmwDTO;
    private Car bmwCopy;
    private Car cla;
    private Car passat;
    private Car octavia;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        bmw = new Car("BMW 3M", "M Blue", Fuel.Petrol, Transmission.Manual);
        bmwCopy = bmw;
        bmwDTO = new CarDTO(bmw.getName(), bmw.getColor(), bmw.getFuel(), bmw.getTransmission());
        cla = new Car("MB CLA AMG", "White", Fuel.Petrol, Transmission.Manual);
        passat = new Car("VW Passat", "Black", Fuel.Diesel, Transmission.Automatic);
        octavia = new Car("Skoda Octavia", "Blue", Fuel.Diesel, Transmission.Automatic);
    }

    @Test
    public void testCreateCar() {
        when(carDao.createCar(any(Car.class))).thenReturn(true);
        when(carDao.findCarById(any(Long.class))).thenReturn(bmw);
        Long createdCarId = carFacade.createCar(bmwDTO);
        assertEquals(null, createdCarId);  
    }

    @Test
    public void testDeleteCar() {        
        when(carDao.deleteCar(any(Car.class))).thenReturn(true);
        when(carDao.findCarById(any(Long.class))).thenReturn(cla);
        boolean expectedResult = carFacade.deleteCar(1l);
        assertEquals(true, expectedResult);        
    }

    @Test
    public void testUpdateName() {
        String expectedName = "BMW 5M";
        bmwCopy.setName(expectedName);
        when(carDao.updateCar(any(Car.class))).thenReturn(bmwCopy);
        when(carDao.findCarById(any(Long.class))).thenReturn(bmw);
        CarDTO updatedResult = carFacade.updateName(1l, expectedName);
        assertEquals(expectedName, updatedResult.getName());
    }

    @Test
    public void testUpdateColor() {        
        String expectedColor = "M Gold";
        bmwCopy.setColor(expectedColor);
        when(carDao.updateCar(any(Car.class))).thenReturn(bmwCopy);
        when(carDao.findCarById(any(Long.class))).thenReturn(bmw);
        CarDTO updatedResult = carFacade.updateColor(1l, expectedColor);
        assertEquals(expectedColor, updatedResult.getColor());
    }

    @Test
    public void testUpdateFuel() {        
        Fuel expectedFuel = Fuel.Diesel;
        bmwCopy.setFuel(expectedFuel);
        when(carDao.updateCar(any(Car.class))).thenReturn(bmwCopy);
        when(carDao.findCarById(any(Long.class))).thenReturn(bmw);
        CarDTO updatedResult = carFacade.updateFuel(1l, expectedFuel);
        assertEquals(expectedFuel, updatedResult.getFuel());
    }

    @Test
    public void testUpdateTransmission() {        
        Transmission expectedTransmission = Transmission.Automatic;
        bmwCopy.setTransmission(expectedTransmission);
        when(carDao.updateCar(any(Car.class))).thenReturn(bmwCopy);
        when(carDao.findCarById(any(Long.class))).thenReturn(bmw);
        CarDTO updatedResult = carFacade.updateTransmission(1l, expectedTransmission);
        assertEquals(expectedTransmission, updatedResult.getTransmission());
    }

    @Test
    public void testGetCarById() {
        when(carDao.findCarById(any(Long.class))).thenReturn(bmwCopy);
        CarDTO result = carFacade.getCarById(1l);
        assertEquals(mappingService.mapTo(bmwCopy, CarDTO.class), result);
    }

    @Test
    public void testGetCarsByFuel() {
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);     
        when(carDao.findCarByFuel(Fuel.Diesel)).thenReturn(expectedResult);
        List<CarDTO> foundCars = carFacade.getCarsByFuel(Fuel.Diesel);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), CarDTO.class), foundCars.get(i));
        }
    }

    @Test
    public void testGetCarsByTransmission() {
        List<Car> expectedResult = new ArrayList<>();
        expectedResult.add(this.passat);
        expectedResult.add(this.octavia);     
        when(carDao.findCarByTransmission(Transmission.Automatic)).thenReturn(expectedResult);
        List<CarDTO> foundCars = carFacade.getCarsByTransmission(Transmission.Automatic);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), CarDTO.class), foundCars.get(i));
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
        List<CarDTO> foundCars = carFacade.getAllCars();
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), CarDTO.class), foundCars.get(i));
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
        List<CarDTO> foundCars = carFacade.getCarsByTransmissionAndFuel(Transmission.Automatic, Fuel.Diesel);
        Assert.assertEquals(expectedResult.size(), foundCars.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), CarDTO.class), foundCars.get(i));
        }
    }
}
