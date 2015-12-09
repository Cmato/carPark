package cz.muni.fi.pa165;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xhubeny2
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ReservationDaoTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    public CarDao carDao;

    private Car car1;
    private Car car2;
    
    @Autowired
    public EmployeeDao employeeDao;

    private Employee empl1;
    private Employee empl2;
    
    @Autowired
    public ReservationDao reservationDao;

    private Reservation res1;
    private Reservation res2;
    
    @BeforeMethod
    public void createContext() {
        
        car1 = new Car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        car2 = new Car("Porsche 911 Turbo", "Red", Fuel.Petrol, Transmission.Manual);

        empl1 = new Employee("Mad Max", DateFormater.newDate(1950, 5, 23), "902154798");
        empl2 = new Employee("Napoleon Solo", DateFormater.newDate(1962, 2, 8), "741369852");

        res1 = new Reservation(empl1, car1, DateFormater.newDate(2014, 9, 19), DateFormater.newDate(2015, 10, 20));
        res2 = new Reservation(empl2, car2, DateFormater.newDate(2015, 3, 3), DateFormater.newDate(2015, 3, 5));
        
        carDao.createCar(car1);
        carDao.createCar(car2);

        employeeDao.createEmployee(empl1);
        employeeDao.createEmployee(empl2);

        reservationDao.create(res1);
        reservationDao.create(res2);
    }
    
    /**
     * Test of nonExistence.
     */ 
    @Test
    @Transactional
    public void testNonExistence(){
        Assert.assertNull(reservationDao.findById(321231231l));
    }

    /**
     * Test of findById method, of ReservationDao class.
     */ 
    @Test
    @Transactional
    public void testFindById() {
        Reservation founded = reservationDao.findById(res1.getId());
            
        Assert.assertEquals(founded.getCar(), res1.getCar());
        Assert.assertEquals(founded.getEmployee(), res1.getEmployee());
        Assert.assertEquals(founded.getEndingDate(), res1.getEndingDate());
        Assert.assertEquals(founded.getStartingDate(), res1.getStartingDate()); 
    }
        

    /**
     * Test of findByEmployee method, of ReservationDao class.
     */ 
    @Test
    @Transactional
    public void testFindByEmployee() {
        List<Reservation> founded = reservationDao.findByEmployee(res1.getEmployee());
            
        Assert.assertEquals(founded.size(), 1);
    }
    
    /**
     * Test of findAll method, of ReservationDao class.
     */
    @Test
    @Transactional
    public void testFindAll() {
        List<Reservation> founded = reservationDao.findAll();
            
        Assert.assertEquals(founded.size(), 2);
    }
    
    /**
     * Test of endAfterStart
     */
    @Test
    @Transactional
    public void testEndAfterStart(){
        Reservation founded = reservationDao.findById(res1.getId());
        Assert.assertTrue(founded.getEndingDate().after(founded.getStartingDate()));
    }
    
    /**
     * Test of update method, of ReservationDao class.
     */  
    @Test
    @Transactional
    public void testUpdate(){
        car1.setName("Trabant");
        res1.setCar(car1);
        Reservation updated = reservationDao.update(res1);
        
        
        Assert.assertEquals(updated.getCar().getName(), "Trabant");
    }
    
    /**
     * Test of remvoe method, of ReservationDao class.
     */  
    @Test
    @Transactional
    public void testRemove() {
        List<Reservation> beforeRemove = reservationDao.findAll();
        reservationDao.remove(res1);
        List<Reservation> afterRemove = reservationDao.findAll();
            
        Assert.assertEquals(beforeRemove.size(), afterRemove.size() + 1);
    }
}
