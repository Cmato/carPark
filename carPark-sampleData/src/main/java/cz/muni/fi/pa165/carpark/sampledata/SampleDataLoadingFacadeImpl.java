package cz.muni.fi.pa165.carpark.sampledata;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.service.ReservationService;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Loads sample data into DB.
 * 
 * @author xhubeny2
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade{

    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    CarService carService;
    
    @Autowired
    RentalService rentalService;
    
    @Autowired
    ReservationService reservationService;
    
    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    
    @Override
    public void loadData() {
        Employee han = employee("Han Solo", "1970MF", toDate(1968, 3, 15), "admin@mail.com", "password",true);
        Employee luke = employee("Luke Skywalker", "4455TT", toDate(1970, 10, 20), "user@mail.com", "password",false);
        Employee obiwan = employee("Obi-Wan Kenobi", "74125NB", toDate(1954, 6, 5), "obi@mail.com", "password",false);
        Employee vader = employee("Darth Vader", "1111DS", toDate(1958, 7, 14), "darth@mail.com", "password",false);
        Employee filip = employee("Filip Novak", "2288RD", toDate(1920, 4, 24), "filip@mail.com", "password",false);
        Employee smazatelny = employee("Pepa pepovic", "11111RD", toDate(1921, 4, 24), "pepa@mail.com", "password",false);
        log.info("Loaded employees.");
        Car mil = car("Millenium Falcon", "grey", Fuel.Diesel, Transmission.Manual);
        Car dStar = car("Death Star", "black", Fuel.Diesel, Transmission.Automatic);
        Car xWing = car("X-Wing", "orange", Fuel.Petrol, Transmission.Manual);
        Car royal = car ("Royal Ship", "silver", Fuel.Diesel, Transmission.Automatic);
        log.info("Loaded cars.");
        Rental r1 = rental(han, mil, toDate(2015, 10, 10), toDate(2015, 10, 20));
        rentalService.updateRentalReturnDate(r1, toDate(2015, 10, 23));
        rentalService.updateRentalState(r1, RentalState.FINISHED);
        Rental r2 = rental(han, mil, toDate(2015, 11, 10), toDate(2015, 11, 15));
        rentalService.updateRentalReturnDate(r2, toDate(2015, 11, 18));
        rentalService.updateRentalState(r2, RentalState.FINISHED);
        Rental r3 = rental(vader, dStar, toDate(2015, 10, 15), toDate(2015, 10, 18));
        rentalService.updateRentalReturnDate(r3, toDate(2015, 10, 18));
        rentalService.updateRentalState(r3, RentalState.FINISHED);
        Rental r4 = rental(luke, xWing, toDate(2015, 11, 14), toDate(2015, 11, 18));
        Rental r5 = rental(obiwan, royal, toDate(2015, 11, 7), toDate(2015, 11, 10));
        Rental r6 = rental(filip, xWing, toDate(2015, 9, 1), toDate(2016, 1, 25));
        Rental r7 = rental(vader, royal, toDate(2015, 10, 1), toDate(2016, 1, 5));
        log.info("Loaded rentals.");
        Reservation res1 = reservation(han, royal, toDate(2016, 2, 1),toDate(2016, 3, 1));
        Reservation res2 = reservation(obiwan, mil, toDate(2016, 3, 5),toDate(2016, 4, 1));
        Reservation res3 = reservation(filip, dStar, toDate(2016, 2, 15),toDate(2016, 4, 1)); 
        log.info("Loaded reservations.");
    }
    
    private static Date toDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date d = c.getTime();
        return d;
    }
    
    private Employee employee(String name, String IdCardNo, Date birth, String email, String password, boolean admin){
        Employee e = new Employee();
        e.setName(name);
        e.setIdCardNumber(IdCardNo);
        e.setBirth(birth);
        e.setEmail(email);
        e.setPassword(password);
        e.setIsAdmin(admin);
        employeeService.createEmployee(e);
        return e;
    }
    
    private Car car(String name, String color, Fuel fuel, Transmission transm){
        Car c = new Car(name, color, fuel, transm);
        carService.createCar(c);
        return c;
    }
    
    private Rental rental(Employee empl, Car car, Date start, Date estimated){
        Rental r = new Rental(empl, car, start, estimated);
        rentalService.createRental(r);
        return r;
    }
    
    private Reservation reservation(Employee empl, Car car, Date start, Date end){
        Reservation r = new Reservation(empl, car, start, end);
        reservationService.createReservation(r);
        return r;
    }
    
}
