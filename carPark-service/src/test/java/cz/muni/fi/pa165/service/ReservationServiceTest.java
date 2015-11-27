package cz.muni.fi.pa165.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;

/**
*
* @author xruzic16
*/
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationServiceTest extends AbstractTestNGSpringContextTests{

	@Mock
    private Reservation reservation;

    @Mock
    private ReservationDao reservationDao;

    @Mock
    private CarAvailability ca;

    @Autowired
    @InjectMocks
    private ReservationService reservationService;
	
	private Reservation res;
    private Car car;
    private Employee empl;
    private Date date1;
    private Date date2;

    @BeforeMethod
    public void createContext() {

        date1 = DateFormater.newDate(1990, 12, 10);
        date2 = DateFormater.newDate(2000, 1, 1);

        car = TestHelper.car("Škoda Henlein", "White", Fuel.Petrol, Transmission.Manual);
        empl = TestHelper.employee("Petr Ètrvrtníèek", date1, "123456789");
        res = TestHelper.reservation(empl, car, date1, date2, ReservationState.NEW);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expectedExceptions = CarParkServiceException.class)
    public void createReservationWithWrongDatesTest() {
        when(reservation.getStartingDate()).thenReturn(date2);
        when(reservation.getEndingDate()).thenReturn(date1);
        reservationService.createReservation(reservation);
    }
    
    @Test
    public void getReservationsByEmployeeTest() {
        when(reservationDao.findByEmployee(any(Employee.class))).thenReturn(Collections.singletonList(res));
        List<Reservation> list = reservationService.getReservationsByEmployee(empl);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getEmployee().getName(), empl.getName());
    }

    @Test
    public void getReservationsByCarTest() {
        when(reservationDao.findByCar(any(Car.class))).thenReturn(Collections.singletonList(res));
        List<Reservation> list = reservationService.getReservationsByCar(car);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getCar().getName(), car.getName());
    }

    @Test
    public void getReservationsByStateTest() {
        when(reservationDao.findByState(any(ReservationState.class))).thenReturn(Collections.singletonList(res));
        List<Reservation> list = reservationService.getReservationsByState(ReservationState.NEW);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getReservationState(), ReservationState.NEW);
    }
    
    @Test
    public void tryAllReservationStatesTest() {
    	Assert.assertEquals(res.getReservationState(), ReservationState.NEW);
    	
    	reservationService.acceptReservation(res);
        Assert.assertEquals(res.getReservationState(), ReservationState.ACCEPTED);
        
        reservationService.denyReservation(res);
        Assert.assertEquals(res.getReservationState(), ReservationState.DENIED);
        
        reservationService.completeReservation(res);
        Assert.assertEquals(res.getReservationState(), ReservationState.DONE);
        
        reservationService.cancelReservation(res);
        Assert.assertEquals(res.getReservationState(), ReservationState.CANCELLED);
        
        reservationService.removeReservation(res);
        Assert.assertEquals(res.getReservationState(), ReservationState.REMOVED);

    }
}
