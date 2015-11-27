/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author xhasprun
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    @InjectMocks
    EmployeeFacade employeeFacade;
    
    EmployeeDTO employee1;
    EmployeeDTO employee2;
    EmployeeDTO employee3;
    
    Date date1;
    Date date2;
    Date date3;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeDao employeeDAO;
    
    private Long testId;
    
    @BeforeMethod
    public void createContext() {
        date1 = new Date(1980, 1, 1);
        date2 = new Date(1983, 2, 2);
        date3 = new Date(1993, 3, 3);
        
        employee1 = TestHelper.employeeDTO("Petr Trava", date1, "1234");
        employee2 = TestHelper.employeeDTO("Trava Petricek", date2, "5678");
        employee3 = TestHelper.employeeDTO("Travel Plava", date3, "9012");
       
    }
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createEmployeeDTOTest(){
        
        employee1.setId(employeeFacade.createEmployee(employee1));
    }

    @Test
    public void deleteEmployeeTest() {
        employee2.setId(employeeFacade.createEmployee(employee2));
        employeeFacade.deleteEmployee(employee2);
    }

    @Test
    public void findEmployeeByIdTest() {
        employee3.setId(employeeFacade.createEmployee(employee3));
        Assert.assertEquals(employeeFacade.findEmployeeById(employee3.getId()), employee3);
    }
    
    
    
    private Date modifyTodaysDate(int modifyValue){
        Date date = new Date();
        date.setTime(date.getTime() + modifyValue);
        return date;
    }
    
    private Date modifyTodaysDatebyDays(int days){
        return modifyTodaysDate(days * 1000 * 60 * 60 * 24);
    }
}
