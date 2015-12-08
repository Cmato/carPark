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
     * @return Car object
     */
    public Car createCar(Car car);

    /**
     * Removes Car
     * @param car object
     */
    public boolean deleteCar(Car car);

    /**
     * Updates Cars name
     * @param updated car
     * @param newName, new name of car
     * @return car obect
     */
    public Car updateName(Car car, String newName);

    /**
     * Updates Cars color
     * @param updated car
     * @param  newColor, new color of car
     * @return car obect
     */
    public Car updateColor(Car car, String newColor);

    /**
     * Updates Cars fuel
     * @param updated car
     * @param  newFuel, new fuel of car
     * @return car obect
     */
    public Car updateFuel(Car car, Fuel newFuel);

    /**
     * Updates Cars transmission
     * @param updated car
     * @param  newTransmission, new transmission of car
     * @return car obect
     */
    public Car updateTransmission(Car car, Transmission newTransmission);

    /**
     * Returns finding Car
     * @param id Car id
     * @return Car object of finding car
     */
    public Car getCarById(Long id);

    /**
     * Returns list of all Cars
     * @return List of Car objects
     */
    public List<Car> getAllCars();

    /**
     * Find Cars by its fuel
     * @param fuel Fuel enum
     * @return List of Car objects
     */
    public List<Car> getCarsByFuel(Fuel fuel);

    /**
     * Find Cars by its transmission
     * @param transmission Transmission enum
     * @return List of Car objects
     */
    public List<Car> getCarsByTransmission(Transmission transmission);

    /**
     * Find Cars by its transmission and fuel
     * @param transmission Transmission enum
     * @param fuel Fuel enum
     * @return List of Car objects
     */
    public List<Car> getCarsByTransmissionAndFuel(Transmission transmission, Fuel fuel);
    
}
