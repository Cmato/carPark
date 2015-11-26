/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xhasprun
 */
public class EmployeeFacadeImpl implements EmployeeFacade{

    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    BeanMappingService beanMappingSevice;
    
    @Override
    public void createEmployee(EmployeeDTO employee) {
        Employee employeeEntity = new Employee();
        employeeEntity.setName(employee.getName());
        employeeEntity.setBirth(employee.getBirth());
        employeeEntity.setIdCardNumber(employee.getIdCardNumber());
        employeeService.createEmployee(employeeEntity);
    }

    @Override
    public void deleteEmployee(EmployeeDTO employee) {
        Employee employeeEntity = new Employee();
        employeeEntity.setName(employee.getName());
        employeeEntity.setBirth(employee.getBirth());
        employeeEntity.setIdCardNumber(employee.getIdCardNumber());
        employeeService.deleteEmployee(employeeEntity);
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        return beanMappingSevice.mapTo(employeeService.findEmployeeById(id), 
                EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        return beanMappingSevice.mapTo(employeeService.findAllEmployees(), 
                EmployeeDTO.class);
    }

    @Override
    public void updateEmployee(EmployeeDTO employee) {
        Employee employeeEntity = new Employee();
        employeeEntity.setName(employee.getName());
        employeeEntity.setBirth(employee.getBirth());
        employeeEntity.setIdCardNumber(employee.getIdCardNumber());
        employeeService.updateEmployee(employeeEntity);
    }
    
}
