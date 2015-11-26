/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author xhasprun
 */
public interface EmployeeFacade {
    /**
     * 
     */
    void createEmployee(EmployeeDTO employee);

    /**
     * 
     */
    void deleteEmployee(EmployeeDTO employee);

    /**
     * 
     */
    EmployeeDTO findEmployeeById(Long id);

    /**
     * 
     */
    List<EmployeeDTO> findAllEmployees();
    
    /**
     * 
     */
    void updateEmployee(EmployeeDTO employee);
}
