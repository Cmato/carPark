package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.dto.CarCreateDTO;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xcmarko
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private CarCreateDTO car;
    
    private Long carId;
    
    @Autowired
    @InjectMocks
    private CarFacade facade;
    
    
    @BeforeMethod
    public void createContext() {
        car = new CarCreateDTO();
        
        car.setColor("White");
        car.setFuel(Fuel.Diesel);
        car.setName("Renault");
        car.setTransmission(Transmission.Manual);
    }
    
    @Test
    @DirtiesContext
    public void createCarTest(){
    	carId = facade.createCar(car);
    }
    
    /*@Test
    @DirtiesContext
    public void getCarByTransmissionTest(){
    	carId = facade.createCar(car);
    	Assert.assertEquals(facade.getCarsByTransmission(Transmission.Manual).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void getCarByFuelTest(){
    	carId = facade.createCar(car);
    	Assert.assertEquals(facade.getCarsByFuel(Fuel.Diesel).size(),1);
    }*/
}
