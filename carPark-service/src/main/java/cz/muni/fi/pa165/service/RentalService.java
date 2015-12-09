package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.Date;
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
     * @return Rental object
     */
    public Rental createRental(Rental rental);

    /**
     * Get all rentals belonging to the given employee.
     *
     * @param employee which has rentals
     * @return List of rentals
     */
    public List<Rental> getRentalsByEmployee(Employee employee);

    /**
     * Get all rentals belonging to the given car.
     *
     * @param car which was rented
     * @return List of rentals
     */
    public List<Rental> getRentalsByCar(Car car);

    /**
     * Get all rentals with the given state.
     *
     * @param rentalState of rentals
     * @return List of rentals
     */
    public List<Rental> getRentalsByState(RentalState rentalState);

    /**
     * Get all rentals.
     *
     * @return List of all rentals
     */
    public List<Rental> getAllRentals();

    /**
     * When employee return a car, rental state is set to finished.
     *
     * @param rental which has been finished
     */
    public void finishRental(Rental rental);

    /**
     * If the rental is after estimated date, then state is set to delayed.
     *
     * @param rental which is delayed
     */
    public void delayRental(Rental rental);

    /**
     * Find rental by ID.
     *
     * @param id of the rental
     * @return rental whith specific ID
     */
    public Rental getRentalById(Long id);
    
    /**
     * Remove specific rental.
     * 
     * @param rental which is removed
     */
    public boolean deleteRental(Rental rental);

    /**
     * Update employee of rental
     * 
     * @param udated rental
     * @param new employee
     */
    public Rental updateRentalEmployee(Rental rental, Employee newEmployee);
    
    /**
     * Update car of rental
     * 
     * @param updated rental
     * @param new car
     */
    public Rental updateRentalCar(Rental rental, Car newCar);

    /**
     * Update starting day of rental
     * 
     * @param updated rental
     * @param new starting date
     */
    public Rental updateRentalStartingDate(Rental rental, Date newDate);

    /**
     * Update return day of rental
     * 
     * @param updated rental
     * @param new return date
     */
    public Rental updateRentalReturnDate(Rental rental, Date newReturnDate);

    /**
     * Update estimated renturn day of rental
     * 
     * @param updated rental
     * @param new estimated return date
     */
    public Rental updateRentalEstimatedReturnDate(Rental rental, Date newEstimatedReturnDate);

    /**
     * Update state of rental
     * 
     * @param updated rental
     * @param new rental state
     */
    public Rental updateRentalState(Rental rental, RentalState newRentalState);
}
