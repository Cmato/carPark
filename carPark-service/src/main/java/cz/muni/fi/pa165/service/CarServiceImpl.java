/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.daos.CarDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;

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
        try {
            carDao.createCar(car);
        } catch (IllegalArgumentException | SecurityException e) {
            throw new CarParkServiceException(e);
        }
    }

    @Override
    public void removeCar(Car car) {
        try {
            carDao.deleteCar(car);
        } catch (IllegalArgumentException | SecurityException e) {
            throw new CarParkServiceException(e);
        }
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
