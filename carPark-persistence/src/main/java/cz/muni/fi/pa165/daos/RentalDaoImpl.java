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
    private EntityManager em;

    @Override
    public Boolean create(Rental rental) {
        validateRental(rental);
        if (em.contains(rental)) {
            return false;
        }
        em.persist(rental);
        em.flush();
        return true;
    }

    @Override
    public boolean remove(Rental rental) {
        if (rental == null) {
            throw new NullPointerException("Rental is null. Nothing to delete");
        }
        validateRental(rental);
        if (!em.contains(rental)) {
            return false;
        }
        em.remove(em.merge(rental));
        return true;
    }

    @Override
    public Rental update(Rental rental) {
        validateRental(rental);
        return em.merge(rental);
    }

    @Override
    public Rental findById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot find Rental. Wrong id parameter given");
        }
        return em.find(Rental.class, id);
    }

    @Override
    public List<Rental> findAll() {     
        return em.createQuery("SELECT r FROM Rental r", Rental.class).getResultList();
    }

    @Override
    public List<Rental> findByEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Cannot find Rentals. Wrong employee parameter given");
        }
        return em.createQuery("SELECT r FROM Rental r WHERE r.employee.id = :id", Rental.class)
                .setParameter("id", employee.getId()).getResultList();
    }

    @Override
    public List<Rental> findByCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Cannot find Rentals. Wrong car parameter given");
        }
        return em.createQuery("SELECT r FROM Rental r WHERE r.car.id = :id", Rental.class)
                .setParameter("id", car.getId()).getResultList();
    }
    
    @Override
    public List<Rental> findRentalsWithState(RentalState rentalState) {
        if (rentalState == null) {
            throw new IllegalArgumentException("Cannot find Rentals. Wrong rental state parameter given");
        }
        return em.createQuery("SELECT r FROM Rental r WHERE r.rentalState = :rentalState", Rental.class)
                .setParameter("rentalState", rentalState).getResultList();
    }

    private void validateRental(Rental rental) throws IllegalArgumentException, NullPointerException {
        if (rental == null) {
            throw new NullPointerException("Given rental is null");
        }

        if (rental.getEmployee() == null) {
            throw new IllegalArgumentException("Employee of Rental cannot be null. Expected Employee Class");
        }
        
        if (rental.getCar() == null) {
            throw new IllegalArgumentException("Car of Rental cannot be null. Expected Car Class");
        }
        
        if (rental.getStartingDate() == null) {
            throw new IllegalArgumentException("Starting date of Rental cannot be null. Expected Date");
        }
        
        if (rental.getEstimatedReturnDate() == null) {
            throw new IllegalArgumentException("Estimated date of Rental cannot be null. Expected Date");
        }

        if (rental.getRentalState() == null) {
            throw new IllegalArgumentException("State of Rental cannot be null. Expected RentalState enum");
        }
    }
    
}
