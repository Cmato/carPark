package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RentalCreateDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.List;

/**
 *
 * @author xcmarko
 */
public interface CarFacade {
    public CarDTO getCarById(Long id);
    public Long createCar(CarCreateDTO car);

    public List<CarDTO> getAllCars();
    public List<CarDTO> getCarsByFuel(Fuel fuel);
    public List<CarDTO> getCarsByTransmission(Transmission transmission);
    
}