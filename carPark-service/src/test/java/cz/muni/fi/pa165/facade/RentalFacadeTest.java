package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalCreateDTO;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.Calendar;
import java.util.Date;
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
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RentalFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RentalFacade rentalFacade;

    @Autowired
    private BeanMappingService bms;

    @Autowired
    private CarDao carDao;

    @Autowired
    private EmployeeDao emplDao;

    @Autowired
    private RentalDao rentalDao;

    private Car car1;
    private Employee empl1;
    private Date date1;
    private Date date2;
    private Date date4;
    private Date date3;
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
        carDto = bms.mapTo(car1, CarDTO.class);
        emplDto = bms.mapTo(empl1, EmployeeDTO.class);

        carDao.createCar(car1);
        emplDao.createEmployee(empl1);
        carDto.setId(carDao.findAllCars().get(0).getId());
        emplDto.setId(emplDao.findAllEmployees().get(0).getId());
        rentalCreateDto1 = new RentalCreateDTO(emplDto, carDto, date1, date2);
        rentalCreateDto2 = new RentalCreateDTO(emplDto, carDto, date3, date4);
    }

    @Test
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
    }
}
