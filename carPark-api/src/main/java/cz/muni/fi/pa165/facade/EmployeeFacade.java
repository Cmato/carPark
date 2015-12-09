package cz.muni.fi.pa165.facade;

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
    public EmployeeDTO updateEmployeeBirth(Long id, Date newBirth);
    public EmployeeDTO updateEmployeeIdCardNumber(Long id, String newIdCarNumber);
    public EmployeeDTO findEmployeeById(Long id);
    public List<EmployeeDTO> findAllEmployees();
    public List<EmployeeDTO> findEmployeesInBirthRange(Date from, Date to);
}
