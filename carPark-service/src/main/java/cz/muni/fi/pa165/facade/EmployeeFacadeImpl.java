package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.MappingService;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xhasprun
 */
@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade{

    @Inject
    private MappingService mappingService;
    
    @Inject
    EmployeeService employeeService;

    @Override
    public Long createEmployee(EmployeeDTO employee) {
        Employee mappedEmployee = mappingService.mapTo(employee, Employee.class);      
        return employeeService.createEmployee(mappedEmployee).getId();
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return employeeService.deleteEmployee(employeeService.findEmployeeById(id));
    }

    @Override
    public EmployeeDTO updateEmployeeName(Long id, String newName) {
        return mappingService.mapTo(employeeService.updateEmployeeName(employeeService.findEmployeeById(id), newName), EmployeeDTO.class);
    }
    
    @Override
    public EmployeeDTO updateEmployeeEmail(Long id, String newEmail) {
        return mappingService.mapTo(employeeService.updateEmployeeEmail(employeeService.findEmployeeById(id), newEmail), EmployeeDTO.class);
    }
    
    @Override
    public EmployeeDTO updateEmployeePassword(Long id, String newPassword) {
        return mappingService.mapTo(employeeService.updateEmployeePassword(employeeService.findEmployeeById(id), newPassword), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeBirth(Long id, Date newBirth) {
        return mappingService.mapTo(employeeService.updateEmployeeBirth(employeeService.findEmployeeById(id), newBirth), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeIdCardNumber(Long id, String newIdCarNumber) {
        return mappingService.mapTo(employeeService.updateEmployeeIdCardNumber(employeeService.findEmployeeById(id), newIdCarNumber), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        return mappingService.mapTo(employeeService.findEmployeeById(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        return mappingService.mapToCollection(employeeService.findAllEmployees(), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findEmployeesInBirthRange(Date from, Date to) {
        return mappingService.mapToCollection(employeeService.findEmployeesInBirthRange(from, to), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String email) {
        return mappingService.mapTo(employeeService.findEmployeeByEmail(email), EmployeeDTO.class);
    }

    @Override
    public boolean authenticate(EmployeeAuthenticateDTO employee) {
        return employeeService.authenticate(
                employeeService.findEmployeeByEmail(employee.getEmail()), employee.getPassword());
    }

    @Override
    public boolean isAdmin(EmployeeDTO employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
