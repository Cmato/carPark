package cz.muni.fi.pa165;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;


import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.utils.DateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author xcmarko
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class EmployeeDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    EmployeeDao DAO;

    private Employee newEmployee(String name, Date date, String idCardNumber) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setBirth(date);
        employee.setIdCardNumber(idCardNumber);

        return employee;
    }

    @DirtiesContext
    @Test
    public void testCreateEmployee(){
        Employee employee = newEmployee("Ignac", DateFormater.newDate(2000, 12, 1), "AA123456");

        DAO.createEmployee(employee);

        Employee sameEmployee = DAO.findEmployeeById(employee.getId());
        assert(employee.equals(sameEmployee));
    }

    @DirtiesContext
    @Test
    public void testDeleteEmployee() {
        Employee employee = newEmployee("Ignacik", DateFormater.newDate(1998, 1, 1), "AA123456");

        DAO.createEmployee(employee);
        DAO.deleteEmployee(employee);
        Employee sameEmployee = DAO.findEmployeeById(employee.getId());
        assert(!employee.equals(sameEmployee) && sameEmployee==null);
    }

    @DirtiesContext
    @Test
    public void testUpdateEmployee() {
        Employee employee = newEmployee("Ignac", DateFormater.newDate(1998, 1, 1), "AA123456");

        DAO.createEmployee(employee);

        employee.setName("Jozko");
        employee.setBirth(DateFormater.newDate(1990, 1, 1));
        employee.setIdCardNumber("FF654321");

        DAO.updateEmployee(employee);

        Employee verifyEmployee = DAO.findEmployeeById(employee.getId());

        assert(employee.equals(verifyEmployee));

    }
}
