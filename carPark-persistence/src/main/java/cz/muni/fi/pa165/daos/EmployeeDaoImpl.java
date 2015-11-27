package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Employee;
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
public class EmployeeDaoImpl implements EmployeeDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long createEmployee(Employee employee) {
        em.persist(employee);
        em.flush();
        return employee.getId();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        em.remove(em.merge(employee));
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT x FROM Employee x", Employee.class).getResultList();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return em.merge(employee);
    }
    
}
