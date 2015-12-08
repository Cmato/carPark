package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.List;

/**
 *
 * @author xcmarko
 */
public interface CarDao {
    
    /**
     * Inserts car into database
     * 
     * @param car to be inserted
     */
    Boolean createCar(Car car);
    
    /**
     * Deletes a specific car from the database
     * 
     * @param car to be deleted
     */
    boolean deleteCar(Car car);
    
    /**
     * Updates attributes of a specific car in database
     * 
     * @param car to be updated
     * @param car object
     */
    Car updateCar(Car car);
    
    /**
     * Retrieves a car with specific id from database
     * 
     * @param id of the car
     * @return car with the given id
     */
    Car findCarById(Long id);
    
    /**
     * Retrieves a cars with specific fuel from database
     * 
     * @param fuel of the car
     * @return list of cars with gived fuel
     */
    List<Car> findCarByFuel(Fuel fuel);
    
    /**
     * Retrieves a cars with specific transmission from database
     * 
     * @param transmission of the car
     * @return list of cars with gived transmission
     */
    List<Car> findCarByTransmission(Transmission transmission);
    
    /**
     * Retrieves all cars from database
     * 
     * @return List of cars
     */
    List<Car> findAllCars();
}
