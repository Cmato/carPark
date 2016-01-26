package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author xhasprun
 */
public interface EmployeeFacade {
    public Long createEmployee(EmployeeDTO employee);
    public boolean deleteEmployee(Long id);
    public EmployeeDTO updateEmployeeName(Long id, String newName);
    public EmployeeDTO updateEmployeeEmail(Long id, String newEmail);
    public EmployeeDTO updateEmployeePassword(Long id, String newPassword);
    public EmployeeDTO updateEmployeeBirth(Long id, Date newBirth);
    public EmployeeDTO updateEmployeeIdCardNumber(Long id, String newIdCarNumber);
    public EmployeeDTO findEmployeeById(Long id);
    public EmployeeDTO findEmployeeByEmail(String email);
    public List<EmployeeDTO> findAllEmployees();
    public List<EmployeeDTO> findEmployeesInBirthRange(Date from, Date to);
    /**
    * Try to authenticate a user. Return true only if the hashed password matches the records.
    */
    boolean authenticate(EmployeeAuthenticateDTO employee);

    /**
    * Check if the given user is admin.
    */
    boolean isAdmin(EmployeeDTO employee);
}
