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
    public Long create(Reservation reservation) {
        em.persist(reservation);
        em.flush();
        return reservation.getId();
    }

    @Override
    public List<Reservation> findAll() {     
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }

    @Override
    public List<Reservation> findByEmployee(Employee employee) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.employee.id = :id", Reservation.class)
                .setParameter("id", employee.getId()).getResultList();
    }
    
    @Override
    public List<Reservation> findByCar(Car car) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.car.id = :id", Reservation.class)
                .setParameter("id", car.getId()).getResultList();
    }

    @Override
    public Reservation findById(Long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public Reservation update(Reservation reservation) {
        return em.merge(reservation);
    }

    @Override
    public void remove(Reservation reservation) {
        em.remove(em.merge(reservation));
    }

	@Override
	public List<Reservation> findByState(ReservationState reservationState) {
		return em.createQuery("SELECT r FROM Reservation r WHERE r.reservationState = :state", Reservation.class)
				.setParameter("state", reservationState).getResultList();
		
	}

}
