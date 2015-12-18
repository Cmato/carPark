package cz.muni.fi.pa165;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.utils.DateFormater;
import java.util.ArrayList;
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
 * @author xcmarko
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    EmployeeDao DAO;
    
    private Employee ignac;
    
    private Employee stefan;

    @BeforeMethod
    public void setUpClass() {
        ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456", "email@gmail.com", "123456");
        stefan = new Employee("Stefan", DateFormater.newDate(1998, 11, 11), "BB654321", "email1@gmail.com", "654321");
        
        //ignac = new Employee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456");
        //stefan = new Employee("Stefan", DateFormater.newDate(1998, 11, 11), "BB654321");
        
        DAO.createEmployee(ignac);
        DAO.createEmployee(stefan);
    }

    /**
     * Test of findEmployeeById method, of EmployeeDao class.
     */ 
    @Test
    @Transactional
    public void testFindEmployeeById() {
        Employee foundEmployee = DAO.findEmployeeById(ignac.getId());
        Assert.assertEquals(ignac, foundEmployee);
    }

    /**
     * Test of findEmployeeByIdCardNumber method, of EmployeeDao class.
     */ 
    @Test
    @Transactional
    public void testFindEmployeeByIdCardNumber() {
        Employee foundEmployee = DAO.findEmployeeByIdCardNumber(ignac.getIdCardNumber());
        Assert.assertEquals(ignac, foundEmployee);
    }

    /**
     * Test of findAll method, of EmployeeDao class.
     */
    @Test
    @Transactional
    public void testFindAllEmployees() {
        List<Employee> foundEmployees = DAO.findAllEmployees();
       
        List<Employee> expectedResult = new ArrayList();
        expectedResult.add(ignac);
        expectedResult.add(stefan);
        
        Assert.assertEquals(foundEmployees.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundEmployees.get(i));
        }
    }

    /**
     * Test of findEmployeesByName method, of EmployeeDao class.
     */
    @Test
    @Transactional
    public void testFindEmployeesByName() {
        List<Employee> foundEmployees = DAO.findEmployeesByName(ignac.getName());
       
        List<Employee> expectedResult = new ArrayList();
        expectedResult.add(ignac);
        
        Assert.assertEquals(foundEmployees.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundEmployees.get(i));
        }
    }

    /**
     * Test of findEmployeesByBirthDate method, of EmployeeDao class.
     */
    @Test
    @Transactional
    public void testFindEmployeesByBirthDate() {
        List<Employee> foundEmployees = DAO.findEmployeesByBirthDate(ignac.getBirth());
       
        List<Employee> expectedResult = new ArrayList();
        expectedResult.add(ignac);
        
        Assert.assertEquals(foundEmployees.size(), expectedResult.size());
        for(int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i), foundEmployees.get(i));
        }
    }

    /**
     * Test of createEmployee method, of EmployeeDao class.
     */  
    @Test
    @Transactional
    public void testCreateEmployee(){
        //if create method works, there have to be ignac already created
        Employee sameEmployee = DAO.findEmployeeById(ignac.getId());
        Assert.assertEquals(ignac, sameEmployee);
    }

    /**
     * Test of deleteEmployee method, of EmployeeDao class.
     */  
    @Test
    @Transactional
    public void testDeleteEmployee() {
        DAO.deleteEmployee(ignac);
        Employee sameEmployee = DAO.findEmployeeById(ignac.getId());
        assert(!ignac.equals(sameEmployee) && sameEmployee==null);
    }

    /**
     * Test of updateEmployee method, of EmployeeDao class.
     */ 
    @Test
    @Transactional
    public void testUpdateEmployee() {
        stefan.setName("Jozko");
        stefan.setBirth(DateFormater.newDate(1990, 1, 1));
        stefan.setIdCardNumber("FF654321");

        DAO.updateEmployee(stefan);

        Employee verifyEmployee = DAO.findEmployeeById(stefan.getId());

        Assert.assertEquals(stefan, verifyEmployee);
    }
}
