package cz.muni.fi.pa165.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.service.CarAvailability;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;

/**
*
* @author xruzic16
*/
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ReservationFacadeTest extends AbstractTestNGSpringContextTests{

	@Mock
    private Reservation reservation;

    @Mock
    private ReservationDao reservationDao;

    @Mock
    private CarAvailability ca;
	
	@Autowired
    @InjectMocks
    private ReservationFacade facade;
	
	@Autowired
    private RentalService rentalService;
	
	@Autowired
    private CarService cService;
	@Autowired
    private EmployeeService eService;
	
	private ReservationDTO rDTO;
	private ReservationCreateDTO crDTO;
	private Long lastResId;
    private CarDTO car;
    private EmployeeDTO empl;
    private Date date1;
    private Date date2;
    
	@BeforeMethod
    public void createContext() {

        date1 = DateFormater.newDate(1990, 12, 10);
        date2 = DateFormater.newDate(2000, 1, 1);

        car = TestHelper.carDTO("Škoda Henlein", "White", Fuel.Petrol, Transmission.Manual);
        empl = TestHelper.employeeDTO("Petr Ètrvrtníèek", date1, "123456789");
        crDTO = new ReservationCreateDTO(empl, car, date1, date2);
       
        
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @DirtiesContext
    public void createReservationTest(){
    	//doNothing().when(ca).checkRentals(any(Reservation.class));
    	lastResId = facade.createReservation(crDTO);
    }
    
    @Test
    @DirtiesContext
    public void getAllReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getAllReservations().size(),1);
    }
    
    @Test
    @DirtiesContext
    public void getByStateReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.NEW).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void getByCarReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getReservationsByCar(new Long(1)).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void getByEmployeeReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getReservationsByEmployee(new Long(1)).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void acceptReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.acceptReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.ACCEPTED).size(),1);
    }
    @Test
    @DirtiesContext
    public void deniedReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.denyReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.DENIED).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void cancelledReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.cancelReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.CANCELLED).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void doneReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.doneReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.DONE).size(),1);
    }
    
    @Test
    @DirtiesContext
    public void removedReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.removeReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.REMOVED).size(),1);
    }
}
