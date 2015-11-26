package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.CarCreateDTO;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;

/**
 *
 * @author xcmarko
 */
public interface CarFacade {
    public CarDTO getCarById(Long id);
    public void createCar(CarCreateDTO car);
    public void removeCar(Long id);
    public List<CarDTO> getAllCars();
    public List<CarDTO> getCarsByFuel(Fuel fuel);
    public List<CarDTO> getCarsByTransmission(Transmission transmission);
    
}