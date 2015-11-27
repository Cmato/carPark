/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;

/**
 *
 * @author xcmarko
 */
@Service
public interface CarService {
    
    /**
     * Creates new Car
     * @param car object
     */
    public Long createCar(Car car);
    
    /**
     * Removes Car
     * @param car object
     */
    public void removeCar(Car car);
    
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
