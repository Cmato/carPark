/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.service.config.MappingConfiguration;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;

/**
 *
 * @author xhasprun
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class EmployeeServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Autowired
    @InjectMocks
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeDao employeeDao;

    private Employee ignac;
    private Employee anotherIgnac;
    private Employee dezo;
    private Employee baltazar;
    
    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456");
        anotherIgnac = ignac;
        dezo = new Employee("Dezko", DateFormater.newDate(1996, 5, 1), "AA654321");
        baltazar = new Employee("Baltazaris", DateFormater.newDate(1989, 8, 1), "FF789456");
    }
    
    @Test
    public void testCreateEmployee() {        
        when(employeeDao.createEmployee(any(Employee.class))).thenReturn(true);
        Employee createdEmployee = employeeService.createEmployee(ignac);       
        assertEquals(ignac, createdEmployee);
    }

    @Test
    public void testCreateExistingEmployee() {
        when(employeeDao.createEmployee(any(Employee.class))).thenReturn(false);
        Employee createdEmployee = employeeService.createEmployee(ignac);  
        assertEquals(null, createdEmployee);
    }

    @Test
    public void testDeleteEmployee() {        
        when(employeeDao.deleteEmployee(any(Employee.class))).thenReturn(true);
        boolean result = employeeService.deleteEmployee(dezo);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteNonExistingEmployee() {        
        when(employeeDao.deleteEmployee(any(Employee.class))).thenReturn(false);
        boolean result = employeeService.deleteEmployee(dezo);
        assertEquals(false, result);
    }

    @Test
    public void testUpdateEmployeeName() {        
        String expectedName = "Igorko";
        anotherIgnac.setName(expectedName);
        when(employeeDao.updateEmployee(ignac)).thenReturn(anotherIgnac);
        Employee updatedResult = employeeService.updateEmployeeName(ignac, expectedName);
        assertEquals(expectedName, updatedResult.getName());
    }

    @Test
    public void testUpdateEmployeeBirth() {        
        Date expectedDate = DateFormater.newDate(1999, 12, 1);
        anotherIgnac.setBirth(expectedDate);
        when(employeeDao.updateEmployee(ignac)).thenReturn(anotherIgnac);
        Employee updatedResult = employeeService.updateEmployeeBirth(ignac, expectedDate);
        assertEquals(expectedDate, updatedResult.getBirth());
    }

    @Test
    public void testUpdateEmployeeIdCardNumber() {        
        String expectedIdCardNumber = "BB123456";
        anotherIgnac.setIdCardNumber(expectedIdCardNumber);
        when(employeeDao.updateEmployee(ignac)).thenReturn(anotherIgnac);
        Employee updatedResult = employeeService.updateEmployeeIdCardNumber(ignac, expectedIdCardNumber);
        assertEquals(expectedIdCardNumber, updatedResult.getIdCardNumber());
    }

    @Test
    public void testGetEmployeeById() {      
        when(employeeDao.findEmployeeById(any(Long.class))).thenReturn(anotherIgnac);
        Employee result = employeeService.findEmployeeById(1l);
        assertEquals(anotherIgnac, result);
    }

    @Test
    public void testFindAllEmployees() {
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(this.ignac);
        expectedResult.add(this.dezo);
        expectedResult.add(this.baltazar);
        when(employeeDao.findAllEmployees()).thenReturn(expectedResult);
        List<Employee> foundEmployees = employeeService.findAllEmployees();
        Assert.assertEquals(expectedResult.size(), foundEmployees.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundEmployees.get(i));
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
        List<Employee> foundEmployees = employeeService.findEmployeesInBirthRange(from, to);
        Assert.assertEquals(expectedResult.size(), foundEmployees.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundEmployees.get(i));
        }
    }

}
