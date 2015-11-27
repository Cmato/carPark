/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import java.util.Date;

/**
 *
 * @author ErikHasprunár
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;
    
    @Override
    public void createEmployee(Employee employee) {
        if (employee.getBirth().after(new Date())) {
            throw new IllegalArgumentException("Employee was not even born yet !!!");
        }
        employeeDao.createEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeDao.deleteEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return findAllEmployees();
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee.getBirth().after(new Date())) {
            throw new IllegalArgumentException("Employee was not even born yet !!!");
        }
        employeeDao.updateEmployee(employee);
    }
    
}
