package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.List;

/**
 * An interface that defines a service access to the {@link Rental} entity.
 *
 * @author xhubeny2
 */
public interface RentalService {

    /**
     * Create new rental.
     *
     * @param rental which is created
     */
    void createRental(Rental rental);

    /**
     * Get all rentals belonging to the given employee.
     *
     * @param employee which has rentals
     * @return List of rentals
     */
    List<Rental> getRentalsByEmployee(Employee employee);

    /**
     * Get all rentals belonging to the given car.
     *
     * @param car which was rented
     * @return List of rentals
     */
    List<Rental> getRentalsByCar(Car car);

    /**
     * Get all rentals with the given state.
     *
     * @param rentalState of rentals
     * @return List of rentals
     */
    List<Rental> getRentalsByState(RentalState rentalState);

    /**
     * Get all rentals.
     *
     * @return List of all rentals
     */
    List<Rental> getAllRentals();

    /**
     * When employee return a car, rental state is set to finished.
     *
     * @param rental which has been finished
     */
    void finishRental(Rental rental);

    /**
     * If the rental is after estimated date, then state is set to delayed.
     *
     * @param rental which is delayed
     */
    void delayRental(Rental rental);

    /**
     * Find rental by ID.
     *
     * @param id of the rental
     * @return rental whith specific ID
     */
    Rental getRentalById(Long id);
    
    /**
     * Remove specific rental.
     * 
     * @param rental which is removed
     */
    void remove(Rental rental);
}
