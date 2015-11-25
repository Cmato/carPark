/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entities.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ErikHasprun�r
 */
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;
    
    public void createEmployee(Employee employee) {
        employeeDao.createEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.deleteEmployee(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    public List<Employee> findAllEmployees() {
        return findAllEmployees();
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }
    
}
