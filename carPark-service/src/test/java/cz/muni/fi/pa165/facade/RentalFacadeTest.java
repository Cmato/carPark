
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalCreateDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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
public class RentalFacadeTest extends AbstractTestNGSpringContextTests {
    
    /*@Mock
    private BeanMappingService bmsMock;
    
    @Mock
    private RentalService rentalService;*/
    
    @Autowired
    //@InjectMocks
    private RentalFacade rentalFacade;
    
    @Autowired
    private BeanMappingService bms;
    
    @Autowired
    private CarDao carDao;
    
    @Autowired
    private EmployeeDao emplDao;
    
    @Autowired
    private RentalDao rentalDao;
    
    private Rental rental1;
    private Car car1;
    private Employee empl1;
    private Date date1;
    private Date date2;
    private Date date4;
    private Date date3;
    private RentalDTO rentalDto;
    private CarDTO carDto;
    private EmployeeDTO emplDto;
    private RentalCreateDTO rentalCreateDto1;
    private RentalCreateDTO rentalCreateDto2;
    
    @BeforeMethod
    public void createContext() {

        Calendar cal = Calendar.getInstance();
        cal.set(1950, 5, 23);
        date1 = cal.getTime();
        cal.set(1962, 2, 8);
        date2 = cal.getTime();
        cal.set(1961, 1, 12);
        date3 = cal.getTime();
        cal.set(1988, 2, 8);
        date4 = cal.getTime();
        cal.set(1989, 1, 12);
       

        car1 = TestHelper.car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        empl1 = TestHelper.employee("Mad Max", date1, "902154798");
        //rental1 = new Rental(empl1, car1, date1, date2);
        //rentalDto = bms.mapTo(rental1, RentalDTO.class);
        carDto = bms.mapTo(car1, CarDTO.class);
        emplDto = bms.mapTo(empl1, EmployeeDTO.class);
        
        carDao.createCar(car1);
        emplDao.createEmployee(empl1);
        carDto.setId(carDao.findAllCars().get(0).getId());
        emplDto.setId(emplDao.findAllEmployees().get(0).getId());
        rentalCreateDto1 = new RentalCreateDTO(emplDto, carDto, date1, date2);
        rentalCreateDto2 = new RentalCreateDTO(emplDto, carDto, date3, date4);
    }
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @DirtiesContext
    public void createRentalTest() {
        rentalFacade.createRental(rentalCreateDto1);
        Assert.assertEquals(rentalDao.findAll().size(), 1);  
    }
    
    @Test
    public void getRentalByIdTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        Long foundId = rentalFacade.getRentalById(createdId).getId();
        Assert.assertEquals(createdId, foundId);
    }
    
    @Test
    public void finishRentalTest(){
        
    }
    
    @Test
    public void getAllRentalsTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.finishRental(createdId);
        rentalFacade.createRental(rentalCreateDto2);
        Assert.assertEquals(rentalFacade.getAllRentals().size(), 2);
    }
    
    @Test
    public void deleteRentalTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.deleteRental(createdId);
        
    }
}
