package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;

/**
 * An interface that defines a service access to the {@link Reservation} entity.
 *
 * @author xruzic16
 */

public interface ReservationService {
	
	/**
     * Create new reservation.
     *
     * @param reservation which is created
     */
    void createReservation(Reservation reservation);
    
    /**
     * Get all reservation belonging to the given employee.
     *
     * @param employee which has reservation
     * @return List of reservations
     */
    List<Reservation> getReservationsByEmployee(Employee employee);

    /**
     * Get all reservations belonging to the given car.
     *
     * @param car which was reserved
     * @return List of reservations
     */
    List<Reservation> getReservationsByCar(Car car);

    /**
     * Get all reservations with the given state.
     *
     * @param reservationState of reservations
     * @return List of reservations
     */
    List<Reservation> getReservationsByState(ReservationState reservationState);

    /**
     * Get all reservations.
     *
     * @return List of all reservations
     */
    List<Reservation> getAllReservations();

    /**
     * Find reservation by ID.
     *
     * @param id of the reservation
     * @return reservation with specific ID
     */
    Reservation getReservationById(Long id);
    
    /**
     * When reservation is accepted and valid state is accepted
     *
     * @param reservation which has been accepted
     */
    void acceptReservation(Reservation reservation);

    /**
     * When reservation is denied for some reason - state is denied
     *
     * @param rental which is denied
     */
    void denyReservation(Reservation reservation);
    
    /**
     * When reservation is cancelled by applicant - state is set to cancelled
     *
     * @param rental which is cancelled
     */
    void cancelReservation(Reservation reservation);
    
    /**
     * When reservation time is here it is moved to rental - state is set to done
     *
     * @param rental which is done
     */
    void completeReservation(Reservation reservation);
    
    /**
     * Set reservation as removed. It can be done by applicant or master - none reservation will be never deleted definitely
     * 
     * @param reservation which will be set as removed
     */
    void removeReservation(Reservation reservation);

}
