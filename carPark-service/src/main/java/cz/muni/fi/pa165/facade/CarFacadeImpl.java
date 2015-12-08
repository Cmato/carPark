package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.MappingService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author xcmarko
 */
@Service
@Transactional
public class CarFacadeImpl implements CarFacade {

    @Inject
    private MappingService mappingService;
    
    @Inject
    private CarService carService;

    @Override
    public Long createCar(CarDTO carCreateDTO) {
        Car mappedCar = mappingService.mapTo(carCreateDTO, Car.class);      
        return carService.createCar(mappedCar).getId();
    }

    @Override
    public boolean deleteCar(Long id) {
        return carService.deleteCar(carService.getCarById(id));
    }

    @Override
    public CarDTO updateName(Long id, String newName) {
        return mappingService.mapTo(carService.updateName(carService.getCarById(id), newName), CarDTO.class);
    }

    @Override
    public CarDTO updateColor(Long id, String newColor) {
        return mappingService.mapTo(carService.updateColor(carService.getCarById(id), newColor), CarDTO.class);
    }

    @Override
    public CarDTO updateFuel(Long id, Fuel newFuel) {
        return mappingService.mapTo(carService.updateFuel(carService.getCarById(id), newFuel), CarDTO.class);
    }

    @Override
    public CarDTO updateTransmission(Long id, Transmission newTransmission) {
        return mappingService.mapTo(carService.updateTransmission(carService.getCarById(id), newTransmission), CarDTO.class);
    }

    @Override
    public CarDTO getCarById(Long id) {
        return mappingService.mapTo(carService.getCarById(id), CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByFuel(Fuel fuel) {
        return mappingService.mapToCollection(carService.getCarsByFuel(fuel), CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByTransmission(Transmission transmission) {
        return mappingService.mapToCollection(carService.getCarsByTransmission(transmission), CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCars() {
        return mappingService.mapToCollection(carService.getAllCars(), CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
        return mappingService.mapToCollection(carService.getCarsByTransmissionAndFuel(transmission, fuel), CarDTO.class);
    }

}
