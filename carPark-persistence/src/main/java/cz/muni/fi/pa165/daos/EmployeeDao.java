/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Employee;
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
    Long createEmployee(Employee employee);

    /**
     * Deletes a specific employee from the database
     * 
     * @param employee employee to delete
     */
    void deleteEmployee(Employee employee);

    /**
     * Retrieves an employee with specific id from database
     * 
     * @param id id of the searched employee
     * @return employee with given id
     */
    Employee findEmployeeById(Long id);

    /**
     * Retrieves all employees from database
     * 
     * @return all employees
     */
    List<Employee> findAllEmployees();
    
    /**
     * Updates attributes of a specific employee in database
     * 
     * @param employee to be updated
     */
    Employee updateEmployee(Employee employee);
}
