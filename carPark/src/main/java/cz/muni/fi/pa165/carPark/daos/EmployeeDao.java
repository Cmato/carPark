/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Employee;
import java.util.List;

/**
 *
 * @author Erik Hasprunar
 */
public interface EmployeeDao {
    void createEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Employee findEmployeeById(Long id);
    List<Employee> findAllEmployees();
}
