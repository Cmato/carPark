package cz.muni.fi.pa165.daos;

import java.util.List;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;

/**
 * Class represents basic CRUD operations with the entity reservation.
 * 
 * @author xruzic16
 */

public interface ReservationDao {
	/**
	 * Inserts reservation into the database
	 * 
	 * @param reservation to be inserted
	 * @return 
	 */
	public Long create(Reservation reservation);

	/**
	 * Retrieves all reservation from the database
	 * 
	 * @return List of reservations
	 */
	public List<Reservation> findAll();

	/**
	 * Retrieves reservations for specific employee
	 * 
	 * @param employee specific for the reservation
	 * @return List of reservations
	 */
	public List<Reservation> findByEmployee(Employee employee);
	
	/**
	 * Retrieves reservations for specific car
	 * 
	 * @param car specific for the reservation
	 * @return List of reservations
	 */
	public List<Reservation> findByCar(Car car);

	/**
	 * Retrieves a reservation with specific id from the database
	 * 
	 * @param id of the reservation
	 * @return reservation with the specific id
	 */
	public Reservation findById(Long id);

	/**
	 * Update specific reservation in the database
	 * 
	 * @param reservation to be updated
	 * @return updated reservation
	 */
	public Reservation update(Reservation reservation);

	/**
	 * Delete specific reservation from the database
	 * 
	 * @param reservation to be deleted
	 */
	public void remove(Reservation reservation);

	/**
	 * Find reservation with specific state
	 * 
	 * @param reservationState to search
	 */
	public List<Reservation> findByState(ReservationState reservationState);
}
