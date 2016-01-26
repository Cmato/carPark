package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
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
@ContextConfiguration(classes = MappingConfiguration.class)
public class RentalServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private Rental rental3;

    @Mock
    private RentalDao rentalDao;

    @Mock
    private CarAvailability ca;

    @Autowired
    @InjectMocks
    private RentalService rentalService;

    private Rental rental1;
    private Car car1;
    private Employee empl1;
    private Date date1;
    private Date date2;

    @BeforeMethod
    public void createContext() {
        date1 = DateFormater.newDate(1950, 5, 23);
        date2 = DateFormater.newDate(1962, 2, 8);

        car1 = new Car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        empl1 = new Employee("Mad Max", date1, "902154798", "email@gmail.com", "123456");
        rental1 = new Rental(empl1, car1, date1, date2);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = CarParkServiceException.class)
    public void createRentalWithWrongDatesTest() {
        when(rental3.getStartingDate()).thenReturn(date2);
        when(rental3.getEstimatedReturnDate()).thenReturn(date1);
        rentalService.createRental(rental3);
    }

    /*@Test(expectedExceptions = CarParkServiceException.class)
    public void createRentalWithNotAvailableCarTest() {
        when(rental3.getStartingDate()).thenReturn(date1);
        when(rental3.getEstimatedReturnDate()).thenReturn(date2);
        //doThrow(CarParkServiceException.class).when(ca).checkRentals(rental3);
        rentalService.createRental(rental3);
    }*/

    @Test 
    public void getRentalsByEmployeeTest() {
        when(rentalDao.findByEmployee(any(Employee.class))).thenReturn(Collections.singletonList(rental1));
        List<Rental> list = rentalService.getRentalsByEmployee(empl1);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getEmployee().getName(), empl1.getName());
    }

    @Test
    public void getRentalsByCarTest() {
        when(rentalDao.findByCar(any(Car.class))).thenReturn(Collections.singletonList(rental1));
        List<Rental> list = rentalService.getRentalsByCar(car1);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getCar().getName(), car1.getName());
    }

    @Test
    public void getRentalsByStateTest() {
        when(rentalDao.findRentalsWithState(any(RentalState.class))).thenReturn(Collections.singletonList(rental1));
        List<Rental> list = rentalService.getRentalsByState(RentalState.ACTIVE);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getRentalState(), RentalState.ACTIVE);
    }

    @Test
    public void finishRentalTest() {
        Calendar calend = Calendar.getInstance();
        calend.set(Calendar.HOUR_OF_DAY, 0);
        calend.set(Calendar.MINUTE, 0);
        calend.set(Calendar.SECOND, 0);
        calend.set(Calendar.MILLISECOND, 0);

        rentalService.finishRental(rental1);
        Assert.assertEquals(rental1.getRentalState(), RentalState.FINISHED);
        Assert.assertEquals(rental1.getReturnDate(), calend.getTime());

        try {
            rentalService.finishRental(rental1);
        } catch (CarParkServiceException e) {
            Assert.assertEquals(e.getMessage(), "The transition from: FINISHED"
                    + " to FINISHED is not allowed!");
        }
    }

    @Test
    public void delayRentalTest() {
        rentalService.delayRental(rental1);
        Assert.assertEquals(rental1.getRentalState(), RentalState.DELAYED);
        try {
            rentalService.delayRental(rental1);
        } catch (CarParkServiceException e) {
            Assert.assertEquals(e.getMessage(), "The transition from: DELAYED"
                    + " to DELAYED is not allowed!");
        }
    }

    /*@Test
    public void deleteRentalTest() {
        rentalService.createRental(rental1);
        System.out.println(rentalService.getAllRentals().size());
        for (Rental rental : rentalService.getAllRentals()) {
            System.out.println(rental.toString());
        }
        //for (rentalService.getAllRentals() : rents) {
            
        //}
        //rentalService.getAllRentals();
    }*/
}
