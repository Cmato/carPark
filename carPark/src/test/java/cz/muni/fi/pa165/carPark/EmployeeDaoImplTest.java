package cz.muni.fi.pa165.carPark;

import cz.muni.fi.pa165.carPark.daos.EmployeeDao;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.utils.DateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author xcmarko
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class EmployeeDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    EmployeeDao DAO;

    @Test
    public void testCreateEmployee(){
        Employee employee = new Employee();
        employee.setName("Ignac");
        employee.setBirth(DateFormater.newDate(2000, 12, 1));
        employee.setIdCardNumber("AA123456");
        DAO.createEmployee(employee);

        Employee sameEmployee = DAO.findEmployeeById(employee.getId());
        assert(employee.equals(sameEmployee));
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setName("Ignacik");
        employee.setBirth(DateFormater.newDate(2000, 12, 1));
        employee.setIdCardNumber("AA123456");

        DAO.createEmployee(employee);
        DAO.deleteEmployee(employee);
        Employee sameEmployee = DAO.findEmployeeById(employee.getId());
        assert(!employee.equals(sameEmployee) && sameEmployee==null);
    }

    /*@Test
    public void testUpdateEmployee() {
        Employee employee = newEmployee("Ignac", DateFormater.newDate(1998, 1, 1), "AA123456");

        DAO.createEmployee(employee);

        employee.setName("Jozko");
        employee.setBirth(DateFormater.newDate(1990, 1, 1));
        employee.setIdCardNumber("FF654321");

        DAO.updateEmployee(employee);

        Employee verifyEmployee = DAO.findEmployeeById(employee.getId());

        assert(employee.equals(verifyEmployee));

    }*/
}
