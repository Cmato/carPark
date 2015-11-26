/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.CarCreateDTO;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.List;

/**
 *
 * @author xcmarko
 */
public interface CarService {
    
    /**
     * Creates new Car
     * @param car 
     */
    public void createCar(CarCreateDTO car);
    
    /**
     * Removes Car
     * @param id 
     */
    public void removeCar(Long id);
    
    /**
     * Returns finding Car
     * @param id Car id
     * @return Car object of finding car
     */
    public Car getCarById(Long id);
    
    /**
     * Returns list of all Cars
     * @return List of CarDTO objects
     */
    public List<Car> getAllCars();
    
    /**
     * Find Cars by its fuel
     * @param fuel Fuel enum
     * @return List of CarDTO objects
     */
    public List<Car> getCarsByFuel(Fuel fuel);
    
    /**
     * Find Cars by its transmission
     * @param transmission Transmission enum
     * @return List of CarDTO objects
     */
    public List<Car> getCarsByTransmission(Transmission transmission);
    
}
