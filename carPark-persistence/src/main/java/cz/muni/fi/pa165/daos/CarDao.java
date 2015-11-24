/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Car;
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
    void createCar(Car car);
    
    /**
     * Deletes a specific car from the database
     * 
     * @param car to be deleted
     */
    void deleteCar(Car car);
    
    /**
     * Updates attributes of a specific car in database
     * 
     * @param car to be updated
     */
    void updateCar(Car car);
    
    /**
     * Retrieves a car with specific id from database
     * 
     * @param id of the car
     * @return car with the given id
     */
    Car findCarById(Long id);
    
    /**
     * Retrieves all cars from database
     * 
     * @return List of cars
     */
    List<Car> findAllCars();
}
