package cz.muni.fi.pa165.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author xhasprun
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Inject
    private EmployeeDao employeeDao;
    
    @Override
    public Employee createEmployee(Employee employee) {
        if(employeeDao.createEmployee(employee)) {
            return employee;
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }

    @Override
    public Employee updateEmployeeName(Employee employee, String newName) {
        if(employee != null && newName != null && !newName.isEmpty()) {
            employee.setName(newName);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployeeBirth(Employee employee, Date newBirth) {
        if(employee != null && newBirth != null) {
            employee.setBirth(newBirth);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployeeIdCardNumber(Employee employee, String newIdCarNumber) {
        if(employee != null && newIdCarNumber != null && !newIdCarNumber.isEmpty()) {
            employee.setIdCardNumber(newIdCarNumber);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }
    
    @Override
    public List<Employee> findEmployeesInBirthRange(Date from, Date to) {
        List<Employee> result = new ArrayList<>();
        List<Employee> allEmployees = this.findAllEmployees();

        if(allEmployees.isEmpty()) {
            return result;
        }

        for(Employee item : allEmployees) {
            Date itemDate = item.getBirth();
            if(itemDate.after(from) && itemDate.before(to)) {
                result.add(item);
            }
        }
        return result;
    }

}
