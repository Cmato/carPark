package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.Calendar;
import java.util.Date;
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
 * @author xhubeny2
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class RentalFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RentalFacade rentalFacade;

    @Autowired
    private MappingService bms;

    @Autowired
    private CarDao carDao;

    @Autowired
    private EmployeeDao emplDao;

    @Mock
    private RentalDao rentalDao;
    
    @Autowired
    @InjectMocks
    private RentalService rentalService;

    private Car car1;
    private Employee empl1;
    private Date date1;
    private Date date2;
    private Date date4;
    private Date date3;
    private CarDTO carDto;
    private EmployeeDTO emplDto;
    private RentalDTO rentalCreateDto1;
    private RentalDTO rentalCreateDto2;

    @BeforeMethod
    public void createContext() {

        date1 = DateFormater.newDate(1950, 5, 23);
        date2 = DateFormater.newDate(1962, 2, 8);
        date3 = DateFormater.newDate(1961, 1, 12);
        date4 = DateFormater.newDate(1988, 2, 8);

        car1 = new Car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        empl1 = new Employee("Mad Max", date1, "902154798");
        carDto = new CarDTO(car1.getName(), car1.getColor(), car1.getFuel(), car1.getTransmission());
        emplDto = new EmployeeDTO(empl1.getName(), empl1.getBirth(), empl1.getIdCardNumber());

        carDao.createCar(car1);
        emplDao.createEmployee(empl1);
        rentalCreateDto1 = new RentalDTO(emplDto, carDto, date1, date2);
        rentalCreateDto2 = new RentalDTO(emplDto, carDto, date3, date4);
    }

    /*@Test
    @DirtiesContext
    public void createRentalTest() {
        rentalFacade.createRental(rentalCreateDto1);
        Assert.assertEquals(rentalDao.findAll().size(), 1);
    }

    @Test
    @DirtiesContext
    public void deleteRentalMethodTest() {
        Assert.assertEquals(rentalDao.findAll().size(), 0);
        Long deletedId = rentalFacade.createRental(rentalCreateDto1);
        Assert.assertEquals(rentalDao.findAll().size(), 1);
        rentalFacade.deleteRental(deletedId);
        Assert.assertEquals(rentalDao.findAll().size(), 0);
    }

    @Test
    @DirtiesContext
    public void getRentalByIdTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        Long foundId = rentalFacade.getRentalById(createdId).getId();
        Assert.assertEquals(createdId, foundId);
    }

    @Test
    @DirtiesContext
    public void finishRentalTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        Assert.assertEquals(rentalDao.findAll().get(0).getRentalState(), RentalState.ACTIVE);
        rentalFacade.finishRental(createdId);
        Assert.assertEquals(rentalDao.findAll().get(0).getRentalState(), RentalState.FINISHED);
    }

    @Test
    @DirtiesContext
    public void delayRentalTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        Assert.assertEquals(rentalDao.findAll().get(0).getRentalState(), RentalState.ACTIVE);
        rentalFacade.delayRental(createdId);
        Assert.assertEquals(rentalDao.findAll().get(0).getRentalState(), RentalState.DELAYED);
    }

    @Test
    @DirtiesContext
    public void getAllRentalsTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.finishRental(createdId);
        rentalFacade.createRental(rentalCreateDto2);
        Assert.assertEquals(rentalFacade.getAllRentals().size(), 2);
    }

    @Test
    @DirtiesContext
    public void getRentalsByCarTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.finishRental(createdId);
        rentalFacade.createRental(rentalCreateDto2);
        Assert.assertEquals(rentalFacade.getRentalsByCar(carDto.getId()).size(), 2);
    }

    @Test
    @DirtiesContext
    public void getRentalsByEmployeeTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.finishRental(createdId);
        rentalFacade.createRental(rentalCreateDto2);
        Assert.assertEquals(rentalFacade.getRentalsByEmployee(emplDto.getId()).size(), 2);
    }

    @Test
    @DirtiesContext
    public void getRentalsByStateTest() {
        Long createdId = rentalFacade.createRental(rentalCreateDto1);
        rentalFacade.finishRental(createdId);
        rentalFacade.createRental(rentalCreateDto2);
        Assert.assertEquals(rentalFacade.getRentalsByState(RentalState.ACTIVE).size(), 1);
        Assert.assertEquals(rentalFacade.getRentalsByState(RentalState.FINISHED).size(), 1);
    }*/
}
