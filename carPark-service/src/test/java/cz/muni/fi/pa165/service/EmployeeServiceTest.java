/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.TestHelper;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author xhasprun
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    @InjectMocks
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeDao employeeDao;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createEmployeeWithWrongDate(){
        Date birth = new Date(3000,2,2);
        //birth.setTime(birth.getTime() + 1000 * 60 * 60 * 7);
        Employee employee = TestHelper.employee("Franta", birth, "123456BR");
        
        employeeService.createEmployee(employee);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateEmployeeWithWrongDate(){
        Date birth = new Date();
        birth.setTime(birth.getTime() + 1000 * 60 * 60 * 7);
        Employee employee = TestHelper.employee("Franta", birth, "123456BR");
        
        employeeService.updateEmployee(employee);
    }
}
