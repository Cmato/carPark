package cz.muni.fi.pa165.facade;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;

/**
 *
 * @author xhasprun
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class EmployeeFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Mock
    private EmployeeDao employeeDao;
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @Autowired
    @InjectMocks
    private EmployeeService employeeService;
    
    @Autowired
    private MappingService mappingService;

    private Employee ignac;
    private EmployeeDTO ignacDTO;
    private Employee anotherIgnac;
    private Employee dezo;
    private Employee baltazar;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456", "email@gmail.com", "123456");
        anotherIgnac = ignac;
        ignacDTO = new EmployeeDTO(ignac.getName(), ignac.getBirth(), ignac.getIdCardNumber(), ignac.getEmail(), ignac.getPassword());
        dezo = new Employee("Dezko", DateFormater.newDate(1996, 5, 1), "AA654321", "email1@gmail.com", "654321");
        baltazar = new Employee("Baltazaris", DateFormater.newDate(1989, 8, 1), "FF789456", "email2@gmail.com", "111222");
    }

    @Test
    public void testCreateEmployee() {        
        when(employeeDao.createEmployee(any(Employee.class))).thenReturn(true);
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(ignac);
        Long createdEmployeeId = employeeFacade.createEmployee(ignacDTO);       
        assertEquals(null, createdEmployeeId);
    }

    @Test
    public void testDeleteEmployee() {        
        when(employeeDao.deleteEmployee(any(Employee.class))).thenReturn(true);
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(dezo);
        boolean expectedResult = employeeFacade.deleteEmployee(1l);
        assertEquals(true, expectedResult);
    }

    @Test
    public void testUpdateName() {
        String expectedName = "Ignacko";
        anotherIgnac.setName(expectedName);
        when(employeeDao.updateEmployee(any(Employee.class))).thenReturn(anotherIgnac);
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(ignac);
        EmployeeDTO updatedResult = employeeFacade.updateEmployeeName(1l, expectedName);
        assertEquals(expectedName, updatedResult.getName());
    }

    @Test
    public void testUpdateEmployeeBirth() {        
        Date expectedDate = DateFormater.newDate(1999, 12, 1);
        anotherIgnac.setBirth(expectedDate);
        when(employeeDao.updateEmployee(any(Employee.class))).thenReturn(anotherIgnac);
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(ignac);
        EmployeeDTO updatedResult = employeeFacade.updateEmployeeBirth(1l, expectedDate);
        assertEquals(expectedDate, updatedResult.getBirth());
    }

    @Test
    public void testUpdateEmployeeIdCardNumber() {        
        String expectedIdCardNumber = "BB123456";
        anotherIgnac.setIdCardNumber(expectedIdCardNumber);
        when(employeeDao.updateEmployee(any(Employee.class))).thenReturn(anotherIgnac);
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(ignac);
        EmployeeDTO updatedResult = employeeFacade.updateEmployeeIdCardNumber(1l, expectedIdCardNumber);
        assertEquals(expectedIdCardNumber, updatedResult.getIdCardNumber());
    }

    @Test
    public void testGetEmployeeById() {      
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(anotherIgnac);
        EmployeeDTO result = employeeFacade.findEmployeeById(1l);
        assertEquals(mappingService.mapTo(anotherIgnac, EmployeeDTO.class), result);
    }

    @Test
    public void testFindAllEmployees() {
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(this.ignac);
        expectedResult.add(this.dezo);
        expectedResult.add(this.baltazar);
        when(employeeDao.findAllEmployees()).thenReturn(expectedResult);
        List<EmployeeDTO> foundEmployees = employeeFacade.findAllEmployees();
        Assert.assertEquals(expectedResult.size(), foundEmployees.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), EmployeeDTO.class), foundEmployees.get(i));
        }
    }

    @Test
    public void testFindEmployeesInBirthRange() {        
        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(this.ignac);
        allEmployees.add(this.dezo);
        allEmployees.add(this.baltazar);
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(this.ignac);
        expectedResult.add(this.dezo);

        Date from = DateFormater.newDate(1990, 1, 1);
        Date to = DateFormater.newDate(2015, 1, 1);

        when(employeeDao.findAllEmployees()).thenReturn(allEmployees);
        List<EmployeeDTO> foundEmployees = employeeFacade.findEmployeesInBirthRange(from, to);
        Assert.assertEquals(expectedResult.size(), foundEmployees.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), EmployeeDTO.class), foundEmployees.get(i));
        }
    }
}
