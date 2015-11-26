package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Employee;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author xhasprun
 */
@Service
public interface EmployeeService {
    /**
     * 
     */
    void createEmployee(Employee employee);

    /**
     * 
     */
    void deleteEmployee(Employee employee);

    /**
     * 
     */
    Employee findEmployeeById(Long id);

    /**
     * 
     */
    List<Employee> findAllEmployees();
    
    /**
     * 
     */
    void updateEmployee(Employee employee);
}
