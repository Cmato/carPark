package cz.muni.fi.pa165;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.Calendar;
import java.util.Date;
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
    
    @Autowired
    public EmployeeDao employeeDao;
    
    @Autowired
    public ReservationDao reservationDao;
    
    
    private Reservation res1;
    private Reservation res2;
    private Car car1;
    private Car car2;
    private Employee empl1;
    private Employee empl2;
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    
    @BeforeMethod
    public void createContext(){
        
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
	date5 = cal.getTime();
        cal.set(2015, 3, 5);
	date6 = cal.getTime();
        
        /*Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 1950);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.DAY_OF_MONTH, 23);*/
        
        car1 = TestHelper.car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        car2 = TestHelper.car("Porsche 911 Turbo", "Red", Fuel.Petrol, Transmission.Manual);
        empl1 = TestHelper.employee("Mad Max", date1, "902154798");
        empl2 = TestHelper.employee("Napoleon Solo", date2, "741369852");
        res1 = TestHelper.reservation(empl1, car1, date3, date4, ReservationState.ACCEPTED);
        res2 = TestHelper.reservation(empl2, car2, date5, date6, ReservationState.ACCEPTED);
        
        carDao.createCar(car1);
        carDao.createCar(car2);
        employeeDao.createEmployee(empl1);
        employeeDao.createEmployee(empl2);
        reservationDao.create(res1);
        reservationDao.create(res2);
    }
    
    @Test
    public void nonExistence(){
        Assert.assertNull(reservationDao.findById(321231231l));
    }
    
    
    @Test
    public void findById() {
        Reservation founded = reservationDao.findById(res1.getId());
            
        Assert.assertEquals(founded.getCar(), res1.getCar());
        Assert.assertEquals(founded.getEmployee(), res1.getEmployee());
        Assert.assertEquals(founded.getEndingDate(), res1.getEndingDate());
        Assert.assertEquals(founded.getStartingDate(), res1.getStartingDate()); 
    }
        
    @Test
    public void findByEmployee() {
        List<Reservation> founded = reservationDao.findByEmployee(res1.getEmployee());
            
        Assert.assertEquals(founded.size(), 1);
    }
    
    @Test
    public void findAll() {
        List<Reservation> founded = reservationDao.findAll();
            
        Assert.assertEquals(founded.size(), 2);
    }
    
    /*@Test
    public void findByCar() {
        List<Reservation> founded = reservationDao.findByCar(res1.getCar());
            
        Assert.assertEquals(founded.size(), 1);
    }*/
    
    @Test
    public void endAfterStart(){
        Reservation founded = reservationDao.findById(res1.getId());
        Assert.assertTrue(founded.getEndingDate().after(founded.getStartingDate()));
    }
    
    @Test
    public void update(){
        car1.setName("Trabant");
        res1.setCar(car1);
        Reservation updated = reservationDao.update(res1);
        
        
        Assert.assertEquals(updated.getCar().getName(), "Trabant");
    }
    
    @Test
    public void remove() {
        List<Reservation> beforeRemove = reservationDao.findAll();
        reservationDao.remove(res1);
        List<Reservation> afterRemove = reservationDao.findAll();
            
        Assert.assertEquals(beforeRemove.size(), afterRemove.size() + 1);
    }
    
    
}
