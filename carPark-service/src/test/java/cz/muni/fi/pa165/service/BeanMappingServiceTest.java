package cz.muni.fi.pa165.service;

//import cz.muni.fi.pa165.config.ServiceConfiguration;

import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    private Rental rental1;
    private Rental rental2;
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
    
    private List<Rental> rentals;
    
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
        
        car1 = TestHelper.car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        car2 = TestHelper.car("Porsche 911 Turbo", "Red", Fuel.Petrol, Transmission.Manual);
        empl1 = TestHelper.employee("Mad Max", date1, "902154798");
        empl2 = TestHelper.employee("Napoleon Solo", date2, "741369852");
        rental1 = new Rental(empl1, car1, date1, date1);
        rental2 = new Rental(empl2, car2, date3, date4);
        
        rentals = new ArrayList<Rental>();
        rentals.add(rental1);
        rentals.add(rental2);
    }
    @Test
    public void mapRentalToRentalDto() {
        RentalDTO rentalDTO = beanMappingService.mapTo(rental1, RentalDTO.class);
        Assert.assertEquals(rental1, beanMappingService.mapTo(rentalDTO, Rental.class),
                "Rental and RentalDTO are different.");
    }

    @Test
    public void mapRentalList() {
        List<RentalDTO> rentalDtos = beanMappingService.mapTo(rentals, RentalDTO.class);
        Assert.assertEquals(rentals.size(), rentalDtos.size(), "Different sizes of lists.");
    }
    
}
