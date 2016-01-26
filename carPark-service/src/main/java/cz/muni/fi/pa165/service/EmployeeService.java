package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Employee;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author xhasprun
 */
@Service
public interface EmployeeService {

    /**
     * Creates new employee
     * @param employee object
     * @return Employee object
     */
    public Employee createEmployee(Employee employee);

    /**
     * Removes employee
     * @param employee object
     */
    public boolean deleteEmployee(Employee employee);

    /**
     * Updates Employees name
     * @param updated employee
     * @param newName, new name of employee
     * @return employee object
     */
    public Employee updateEmployeeName(Employee employee, String newName);
    
    /**
     * Updates Employees email
     * @param updated employee
     * @param newEmail, new email of employee
     * @return employee object
     */
    public Employee updateEmployeeEmail(Employee employee, String newEmail);
    
    /**
     * Updates Employees password
     * @param updated employee
     * @param newPassword, new email of employee
     * @return employee object
     */
    public Employee updateEmployeePassword(Employee employee, String newPassword);

    /**
     * Updates Employees birth
     * @param updated employee
     * @param newBirth, new birth date of employee
     * @return employee object
     */
    public Employee updateEmployeeBirth(Employee employee, Date newBirth);

    /**
     * Updates Employees id card numnber
     * @param updated employee
     * @param newIdCarNumber, new id card numnber of employee
     * @return employee obect
     */
    public Employee updateEmployeeIdCardNumber(Employee employee, String newIdCarNumber);

    /**
     * Returns finding employee
     * @param id Employee id
     * @return Employee object
     */
    public Employee findEmployeeById(Long id);
    
    /**
     * Returns finding employee
     * @param email Employee email
     * @return Employee object
     */
    public Employee findEmployeeByEmail(String email);

    /**
     * Finds list of all Employees
     * @return List of Employee objects
     */
    public List<Employee> findAllEmployees();

    /**
     * Find employees who were born between given date range
     * @param from, date from
     * @param to, date to
     * @return List of Employee objects
     */
    public List<Employee> findEmployeesInBirthRange(Date from, Date to);
    
    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     */
    boolean authenticate(Employee employee, String password);

    /**
     * Check if the given user is admin.
     */
    boolean isAdmin(Employee employee);
}
