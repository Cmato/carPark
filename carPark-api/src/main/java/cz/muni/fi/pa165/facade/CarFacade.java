package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;

/**
 *
 * @author xcmarko
 */
public interface CarFacade {
    public Long createCar(CarDTO car);
    public boolean deleteCar(Long id);
    public CarDTO updateName(Long id, String newName);
    public CarDTO updateColor(Long id, String newColor);
    public CarDTO updateFuel(Long id, Fuel newFuel);
    public CarDTO updateTransmission(Long id, Transmission newTransmission);
    public CarDTO getCarById(Long id);
    public List<CarDTO> getAllCars();
    public List<CarDTO> getCarsByFuel(Fuel fuel);
    public List<CarDTO> getCarsByTransmission(Transmission transmission);
    public List<CarDTO> getCarsByTransmissionAndFuel(Transmission transmission, Fuel fuel);
}