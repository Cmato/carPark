package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Employee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author xhasprun
 */
public interface EmployeeDao {

    /**
     * Inserts employee into database
     * 
     * @param employee employee to insert
     */
    Boolean createEmployee(Employee employee);

    /**
     * Deletes a specific employee from the database
     * 
     * @param employee employee to delete
     */
    boolean deleteEmployee(Employee employee);

    /**
     * Updates attributes of a specific employee in database
     * 
     * @param employee to be updated
     */
    Employee updateEmployee(Employee employee);

    /**
     * Retrieves an employee with specific id from database
     * 
     * @param id id of the searched employee
     * @return employee with given id
     */
    Employee findEmployeeById(Long id);

    /**
     * Retrieves an employee with specific id card number from database
     * 
     * @param idCardNumber card number of the searched employee
     * @return employee with given id card number
     */
    Employee findEmployeeByIdCardNumber(String idCardNumber);

    /**
     * Retrieves an employees with specific name from database
     * 
     * @param name of the searched employee
     * @return employees list with given name
     */
    List<Employee> findEmployeesByName(String name);

    /**
     * Retrieves an employees with specific birth date from database
     * 
     * @param birth date of the searched employee
     * @return employees list with given birth date
     */
    List<Employee> findEmployeesByBirthDate(Date birth);

    /**
     * Retrieves all employees from database
     * 
     * @return all employees
     */
    List<Employee> findAllEmployees();
}
