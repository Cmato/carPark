package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Rental;
import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
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
     */
    public void create(Rental rental);
    
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
