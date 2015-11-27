
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.service.TestHelper;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xhubeny2
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RentalFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private BeanMappingService bms;
    
    @Mock
    private RentalService rentalService;
    
    @Autowired
    @InjectMocks
    private RentalFacade rentalFacade;
    
    private Rental rental1;
    private Car car1;
    private Employee empl1;
    private Date date1;
    private Date date2;

    @BeforeMethod
    public void createContext() {

        Calendar cal = Calendar.getInstance();
        cal.set(1950, 5, 23);
        date1 = cal.getTime();
        cal.set(1962, 2, 8);
        date2 = cal.getTime();
        cal.set(1961, 1, 12);

        car1 = TestHelper.car("Ford Mustang", "Black", Fuel.Diesel, Transmission.Automatic);
        empl1 = TestHelper.employee("Mad Max", date1, "902154798");
        rental1 = new Rental(empl1, car1, date1, date2);
    }
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    /*@Test
    public void getRentalByIdTest(){
        when(rentalService.getRentalById(any(Long.class))).thenReturn(bms.mapTo(, Weapon.class));
        weaponFacade.getWeaponById(1l);
        verify(weaponService, times(1)).findWeaponById(1l);   
    }*/
    
    @Test
    public void createRentalTest(){
        
    }
}
