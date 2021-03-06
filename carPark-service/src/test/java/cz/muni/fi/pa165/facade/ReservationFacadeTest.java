package cz.muni.fi.pa165.facade;

import java.util.Date;

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

import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.CarAvailability;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;

/**
*
* @author xruzic16
*/
@ContextConfiguration(classes = MappingConfiguration.class)
public class ReservationFacadeTest extends AbstractTestNGSpringContextTests{

    @Mock
    private Reservation reservation;

    @Mock
    private ReservationDao reservationDao;

    @Mock
    private CarAvailability ca;
	
    @Autowired
    private ReservationFacade facade;
    
    @Autowired
    private CarFacade cfacade;
    @Autowired
    private EmployeeFacade efacade;
        
    @Autowired
    @InjectMocks
    private ReservationFacade reservationService;
    
    private ReservationDTO crDTO;
    private Long lastResId;
    private CarDTO car;
    private EmployeeDTO empl;
    private Date date1;
    private Date date2;
    
    @BeforeMethod
    public void createContext() {

        date1 = DateFormater.newDate(1990, 11, 10);
        date2 = DateFormater.newDate(2000, 3, 1);
        
        car = new CarDTO("Schoda Henlein", "White", Fuel.Petrol, Transmission.Manual);
        empl = new EmployeeDTO("Petr Ctvrtnicek", date1, "123456789", "email@gmail.com", "123456");
        
        cfacade.createCar(car);
        efacade.createEmployee(empl);
        
        crDTO = new ReservationDTO(efacade.findEmployeeById(new Long(1)), cfacade.getCarById(new Long(1)), date1, date2);
        
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    /*@Test
    @DirtiesContext
    public void createAndGetAllReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getAllReservations().size(),1);
    }
    
    @Test
    @DirtiesContext
    public void getByStateReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.ACTIVE).size(),1);
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
    public void cancelledReservationTest(){
    	lastResId = facade.createReservation(crDTO);
    	facade.cancelReservation(lastResId);
    	Assert.assertEquals(facade.getReservationsByState(ReservationState.CANCELLED).size(),1);
    }*/
}
