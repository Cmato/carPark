package cz.muni.fi.pa165.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;

/**
*
* @author xruzic16
*/
@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean create(Reservation reservation) {
        validateReservation(reservation);
        if (em.contains(reservation)) {
            return false;
        }
        em.persist(reservation);
        em.flush();
        return true;
    }

    @Override
    public boolean remove(Reservation reservation) {
        if (reservation == null) {
            throw new NullPointerException("Reservation is null. Nothing to delete");
        }
        validateReservation(reservation);
        if (!em.contains(reservation)) {
            return false;
        }
        em.remove(em.merge(reservation));
        return true;
    }

    @Override
    public Reservation update(Reservation reservation) {
        validateReservation(reservation);
        return em.merge(reservation);
    }

    @Override
    public List<Reservation> findAll() {     
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> findByEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Cannot find Reservations. Wrong employee parameter given");
        }
        return em.createQuery("SELECT r FROM Reservation r WHERE r.employee.id = :id", Reservation.class)
                .setParameter("id", employee.getId()).getResultList();
    }
    
    @Override
    public List<Reservation> findByCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Cannot find Reservations. Wrong car parameter given");
        }
        return em.createQuery("SELECT r FROM Reservation r WHERE r.car.id = :id", Reservation.class)
                .setParameter("id", car.getId()).getResultList();
    }

    @Override
    public Reservation findById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot find Reservation. Wrong id parameter given");
        }
        return em.find(Reservation.class, id);
    }

	@Override
	public List<Reservation> findByState(ReservationState reservationState) {
        if (reservationState == null) {
            throw new IllegalArgumentException("Cannot find Reservations. Wrong reservationState parameter given");
        }
		return em.createQuery("SELECT r FROM Reservation r WHERE r.reservationState = :state", Reservation.class)
				.setParameter("state", reservationState).getResultList();
		
	}

    private void validateReservation(Reservation reservation) throws IllegalArgumentException, NullPointerException {
        if (reservation == null) {
            throw new NullPointerException("Given Reservation is null");
        }

        if (reservation.getEmployee() == null) {
            throw new IllegalArgumentException("Employee of reservation cannot be null. Expected Employee Class");
        }
        
        if (reservation.getCar() == null) {
            throw new IllegalArgumentException("Car of reservation cannot be null. Expected Car Class");
        }
        
        if (reservation.getStartingDate() == null) {
            throw new IllegalArgumentException("Starting date of reservation cannot be null. Expected Date");
        }
        
        if (reservation.getEndingDate() == null) {
            throw new IllegalArgumentException("Ending date of reservation cannot be null. Expected Date");
        }

        if (reservation.getReservationState() == null) {
            throw new IllegalArgumentException("State of reservation cannot be null. Expected ReservationState Class");
        }
    }

}
