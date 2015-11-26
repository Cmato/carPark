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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xcmarko
 */
public class CarServiceImpl implements CarService{
    
    @Autowired
    private CarDao carDao;

    @Override
    public void createCar(CarCreateDTO car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
