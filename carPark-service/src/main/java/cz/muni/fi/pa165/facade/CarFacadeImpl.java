/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarCreateDTO;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xcmarko
 */
public class CarFacadeImpl implements CarFacade {
    @Autowired
    BeanMappingService beanMappingSevice;
            
    @Autowired
    CarService carService;

    @Override
    public CarDTO getCarById(Long id) {
        return beanMappingSevice.mapTo(carService.getCarById(id),
                CarDTO.class);
    }

    @Override
    public void createCar(CarCreateDTO carDTO) {
        Car car = new Car();
        car.setName(carDTO.getName());
        car.setColor(carDTO.getColor());
        car.setFuel(carDTO.getFuel());
        car.setTransmission(carDTO.getTransmission());
        carService.createCar(car);
    }

    @Override
    public void removeCar(Long id) {
        carService.removeCar(carService.getCarById(id));
    }

    @Override
    public List<CarDTO> getAllCars() {
        return beanMappingSevice.mapTo(carService.getAllCars(),
                CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByFuel(Fuel fuel) {
        return beanMappingSevice.mapTo(carService.getCarsByFuel(fuel),
                CarDTO.class);
    }

    @Override
    public List<CarDTO> getCarsByTransmission(Transmission transmission) {
        return beanMappingSevice.mapTo(carService.getCarsByTransmission(transmission),
                CarDTO.class);
    }
    
}
