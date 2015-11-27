package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.List;

/**
 * Class represents basic CRUD operations with the entity rental.
 * 
 * @author xhubeny2
 */
public interface RentalDao {
    /**
     * Inserts rental into the database
     * @param rental to be inserted
     * @return ID of the new rental
     */
    public Long create(Rental rental);
    
    /**
     * Retrieves all rental from the database
     * @return List of rentals
     */
    public List<Rental> findAll();
    
    /**
     * Retrieves rentals for specific employee
     * @param employee specific for the rentals
     * @return List of rentals
     */
    public List<Rental> findByEmployee(Employee employee);
    
    /**
     * Retrieves rentals for specific car
     * @param car specific for the rentals
     * @return List of rentals
     */
    public List<Rental> findByCar(Car car);
    
    /**
     * Retrieves a rental with specific id from the database
     * @param id of the rental
     * @return rental with the specific id
     */
    public Rental findById(Long id);
    
    /**
     * Retrieves a rentals with specific state from the database
     * @param rentalState of the rentals
     * @return rental with the specific state
     */
    public List<Rental> findRentalsWithState(RentalState rentalState);
    
    /**
     * Update specific rental in the database
     * @param rental to be updated
     * @return updated rental
     */
    public Rental update(Rental rental);
    
    /**
     * Delete specific rental from the database
     * @param rental to be deleted
     */
    public void remove(Rental rental);
       
}
