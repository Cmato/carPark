package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
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
        return em.createQuery("SELECT r FROM Rental r", Rental.class).getResultList();
    }

    @Override
    public List<Rental> findByEmployee(Employee employee) {
        return em.createQuery("SELECT r FROM Rental r WHERE r.employee.id = :id", Rental.class)
                .setParameter("id", employee.getId()).getResultList();
    }

    @Override
    public List<Rental> findByCar(Car car) {
        return em.createQuery("SELECT r FROM Rental r WHERE r.car.id = :id", Rental.class)
                .setParameter("id", car.getId()).getResultList();
    }

    @Override
    public Rental findById(Long id) {
        return em.find(Rental.class, id);
    }
    
    @Override
    public List<Rental> findRentalsWithState(RentalState rentalState) {
        return em.createQuery("SELECT r FROM Rental r WHERE r.rentalState = :rentalState", Rental.class)
		.setParameter("rentalState", rentalState).getResultList();
        /*
        TypedQuery<Rental> query = em.createQuery(
				"SELECT r FROM Rental r WHERE r.state = :state", Rental.class);
		query.setParameter("state", state);
		return query.getResultList();
        */
    }

    @Override
    public Rental update(Rental rental) {
        return em.merge(rental);
    }

    @Override
    public void remove(Rental rental) {
        em.remove(em.merge(rental));
    }
    
}
