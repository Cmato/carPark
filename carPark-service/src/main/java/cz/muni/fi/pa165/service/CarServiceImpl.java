package cz.muni.fi.pa165.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.daos.CarDaoImpl;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author xcmarko
 */
@Service
@ComponentScan(basePackageClasses={CarDaoImpl.class})
public class CarServiceImpl implements CarService {
    
    @Inject
    private CarDao carDao;

    @Override
    public Car createCar(Car car) {
        if(carDao.createCar(car)) {
            return car;
        }
        return null;
    }

    @Override
    public boolean deleteCar(Car car) {
        if (car != null) {
            return carDao.deleteCar(car);
        }
        return false;
    }

    @Override
    public Car updateName(Car car, String newName) {
        if(car != null && newName != null && !newName.isEmpty()) {
            car.setName(newName);
            return carDao.updateCar(car);
        }
        return null;
    }

    @Override
    public Car updateColor(Car car, String newColor) {
        if(car != null && newColor != null && !newColor.isEmpty()) {
            car.setColor(newColor);
            return carDao.updateCar(car);
        }
        return null;
    }

    @Override
    public Car updateFuel(Car car, Fuel newFuel) {
        if(car != null && newFuel != null) {
            car.setFuel(newFuel);
            return carDao.updateCar(car);
        }
        return null;
    }

    @Override
    public Car updateTransmission(Car car, Transmission newTransmission) {
        if(car != null && newTransmission != null) {
            car.setTransmission(newTransmission);
            return carDao.updateCar(car);
        }
        return null;
    }

    @Override
    public Car getCarById(Long id) {
        return carDao.findCarById(id);
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.findAllCars();
    }

    @Override
    public List<Car> getCarsByFuel(Fuel fuel) {
        return carDao.findCarByFuel(fuel);
    }

    @Override
    public List<Car> getCarsByTransmission(Transmission transmission) {
        return carDao.findCarByTransmission(transmission);
    }

    @Override
    public List<Car> getCarsByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
        List<Car> result = new ArrayList<>();
        List<Car> allCarsByTransmission = this.getCarsByTransmission(transmission);

        if(allCarsByTransmission.isEmpty()) {
            return result;
        }

        for(Car item : allCarsByTransmission) {
            if(item.getFuel() == fuel) {
                result.add(item);
            }
        }
        return result;
    }
    
}
