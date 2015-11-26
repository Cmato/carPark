/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.dto.CarCreateDTO;
import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xcmarko
 */
@Service
public class CarServiceImpl implements CarService{
    
    @Autowired
    private CarDao carDao;

    @Override
    public void createCar(Car car) {
        carDao.createCar(car);
    }

    @Override
    public void removeCar(Car car) {
        carDao.deleteCar(car);
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
    
}
