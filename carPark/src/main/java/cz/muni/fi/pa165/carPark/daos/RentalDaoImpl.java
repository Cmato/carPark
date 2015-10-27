package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.entities.Rental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xhubeny2
 */
@Repository
@Transactional
public class RentalDaoImpl implements RentalDao{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Rental rental) {
        em.persist(rental);
    }

    @Override
    public List<Rental> findAll() {
        //return em.createQuery("select r from Rental r", Rental.class).getResultList();
        return null;
    }

    @Override
    public List<Rental> findByEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rental> findByCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rental findById(Long id) {
        return em.find(Rental.class, id);
    }

    @Override
    public Rental update(Rental rental) {
        return em.merge(rental);
    }

    @Override
    public void remove(Rental rental) {
        em.remove(rental);
    }
    
}
