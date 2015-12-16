package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;
import java.util.Date;

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
	 * @return 
     */
    public Reservation createReservation(Reservation reservation);
    
    /**
     * Get all reservation belonging to the given employee.
     *
     * @param employee which has reservation
     * @return List of reservations
     */
    public List<Reservation> getReservationsByEmployee(Employee employee);

    /**
     * Get all reservations belonging to the given car.
     *
     * @param car which was reserved
     * @return List of reservations
     */
    public List<Reservation> getReservationsByCar(Car car);

    /**
     * Get all reservations with the given state.
     *
     * @param reservationState of reservations
     * @return List of reservations
     */
    public List<Reservation> getReservationsByState(ReservationState reservationState);

    /**
     * Get all reservations.
     *
     * @return List of all reservations
     */
    public List<Reservation> getAllReservations();

    /**
     * Find reservation by ID.
     *
     * @param id of the reservation
     * @return reservation with specific ID
     */
    public Reservation getReservationById(Long id);
    
    /**
     * When reservation is cancelled by applicant - state is set to cancelled
     *
     * @param reservation which is cancelled
     */
    public void cancelReservation(Reservation reservation);

    /**
     * Update employee of reservation
     * 
     * @param udated reservation
     * @param new employee
     */
    public Reservation updateReservationEmployee(Reservation reservation, Employee newEmployee);
    
    /**
     * Update car of reservation
     * 
     * @param updated reservation
     * @param new car
     */
    public Reservation updateReservationCar(Reservation reservation, Car newCar);
    
    /**
     * Update starting day of reservation
     * 
     * @param updated reservation
     * @param new starting date
     */
    public Reservation updateReservationStartingDate(Reservation reservation, Date newDate);
    
    /**
     * Update ending day of reservation
     * 
     * @param updated reservation
     * @param new ending date
     */
    public Reservation updateReservationEndingDate(Reservation reservation, Date newDate);


    /**
     * Update state of reservation
     * 
     * @param updated reservation
     * @param new reservation state
     */
    public Reservation updateReservationState(Reservation reservation, ReservationState newState);

}
