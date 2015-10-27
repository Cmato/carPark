package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Erik Hasprunar
 */
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createEmployee(Employee employee) {
        em.persist(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        em.remove(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        //TODO !!!interface problem!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
