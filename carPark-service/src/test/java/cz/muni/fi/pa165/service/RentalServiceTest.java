package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xhubeny2
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RentalServiceTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private Car car;
    
    @Mock
    private Employee employee;
    
    @Mock
    private Rental rental3;
    
    
    @Autowired
    @InjectMocks
    private RentalService rentalService;
    
    private Rental rental1;
    private Rental rental2;
    private Car car1;
    private Car car2;
    private Employee empl1;
    private Employee empl2;
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    
    
    @BeforeMethod
    public void createContext() {

        Calendar cal = Calendar.getInstance();
        cal.set(1950, 5, 23);
        date1 = cal.getTime();
        cal.set(1962, 2, 8);
        date2 = cal.getTime();
        cal.set(2014, 9, 19);
        date3 = cal.getTime();
        cal.set(2015, 10, 20);
        date4 = cal.getTime();
        cal.set(2015, 3, 3);

        car1 = TestHelper.car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        car2 = TestHelper.car("Porsche 911 Turbo", "Red", Fuel.Petrol, Transmission.manual);
        empl1 = TestHelper.employee("Mad Max", date1, "902154798");
        empl2 = TestHelper.employee("Napoleon Solo", date2, "741369852");
        rental1 = new Rental(empl1, car1, date1, date1);
        rental2 = new Rental(empl2, car2, date3, date4);
    }
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    
    

    @Test
    public void pokus(){
        employee.setName("Adam");
        when(employee.getName()).thenReturn("Adam");
    }
    
    @Test
    public void createRentalTest(){
        
    }
    
    @Test
    public List<Rental> getRentalsByEmployeeTest(Employee employee){
        
    }
    
    @Test
    public List<Rental> getRentalsByCarTest(Car car){
        
    }
    
    @Test
    public List<Rental> getRentalsByStateTest(){
        
    }
    
    @Test
    public void finishRentalTest(){
        
    }
    
    @Test
    public void delayRentalTest(){
        /*rentalService.delayRental(rental1);
        //when(rentalService.getRentalById(rental1.getId()).getRentalState()).thenReturn(RentalState.DELAYED);
        Assert.assertEquals(rental1.getRentalState(), RentalState.DELAYED);
        try{
            rentalService.delayRental(rental1);
        }
        catch(CarParkServiceException e){
            Assert.assertEquals(e.getMessage(), "The transition from: DELAYED"
                    + " to DELAYED is not allowed!");
        }*/
        //when(rentalService.delayRental(rental1), rental1.getRentalState()).
        //doThrow(CarParkServiceException.class).when(rentalService).delayRental(rental1);
        //rental1.setRentalState(RentalState.ACTIVE);
        //rental3
        //rental3 = new Rental(any(Employee.class), any(Car.class), any(Date.class), any(Date.class));
        rentalService.delayRental(rental1);
        when(rental1.getRentalState()).thenReturn(RentalState.DELAYED);
    }

    
}
