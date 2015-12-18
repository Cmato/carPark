package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Employee;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author xhasprun
 */
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean createEmployee(Employee employee) {
        validateEmployee(employee);
        if (em.contains(employee)) {
            return false;
        }
        em.persist(employee);
        em.flush();
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee is null. Nothing to delete");
        }
        validateEmployee(employee);
        if (!em.contains(employee)) {
            return false;
        }
        em.remove(em.merge(employee));
        return true;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        validateEmployee(employee);
        return em.merge(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot find Employee. Wrong id parameter given");
        }
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public Employee findEmployeeByIdCardNumber(String idCardNumber) {
        if (idCardNumber == null) {
            throw new IllegalArgumentException("Cannot find Employee. Wrong idCardNumber parameter given");
        }
        return em.createQuery("SELECT e FROM Employee e WHERE e.idCardNumber = :idCardNumber", Employee.class)
                .setParameter("idCardNumber", idCardNumber).getSingleResult();
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot find Employee. Wrong name parameter given");
        }
        return em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public List<Employee> findEmployeesByBirthDate(Date birth) {
        if (birth == null) {
            throw new IllegalArgumentException("Cannot find Employee. Wrong birth date parameter given");
        }
        return em.createQuery("SELECT e FROM Employee e WHERE e.birth = :birth", Employee.class)
                .setParameter("birth", birth).getResultList();
    }
    
    private void validateEmployee(Employee employee) throws IllegalArgumentException, NullPointerException {
        if (employee == null) {
            throw new NullPointerException("Given employee is null");
        }

        if (employee.getName() == null) {
            throw new IllegalArgumentException("Name of employee cannot be null. Expected String");
        }
        
        if (employee.getIdCardNumber() == null) {
            throw new IllegalArgumentException("Id card number of employee cannot be null. Expected String");
        }
        
        if (employee.getBirth() == null) {
            throw new IllegalArgumentException("Birth date of employee cannot be null. Expected Date");
        }
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find Employee. Wrong email parameter given");
        }
        return em.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
                .setParameter("email", email).getSingleResult();
    }
}
