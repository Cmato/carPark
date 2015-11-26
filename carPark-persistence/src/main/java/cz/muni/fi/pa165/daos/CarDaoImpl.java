package cz.muni.fi.pa165.daos;

import cz.muni.fi.pa165.entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import cz.muni.fi.pa165.entities.Reservation;
import org.springframework.stereotype.Repository;

/**
 *
 * @author xcmarko
 */
@Repository
@Transactional
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createCar(Car car) {
        /*validateCar(car);
        if(car.getId() != null) {
            throw new IllegalArgumentException("Cannot create car with assigned id");
        }*/
        em.persist(car);
    }

    @Override
    public void deleteCar(Car car) {
        /*if (car == null) {
            throw new NullPointerException("Car is null. Nothing to delete");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Cannot delete car with null id. Nothing to delete");
        }*/
        em.remove(em.merge(car));
    }

    @Override
    public void updateCar(Car car) {
        //validateCar(car);
        em.merge(car);
    }

    @Override
    public Car findCarById(Long id) {
        return em.find(Car.class, id);
    }

    @Override
    public List<Car> findAllCars() {
        return em.createQuery("SELECT r FROM Car r", Car.class).getResultList();
    }
    
    private void validateCar(Car car) throws IllegalArgumentException, NullPointerException {
        if(car == null) {
            throw new NullPointerException("Given car is null");
        }

        if (car.getName()== null) {
            throw new IllegalArgumentException("Name of car cannot be null. Expected String");
        }
        
        if(car.getColor() == null) {
            throw new IllegalArgumentException("Color of car cannot be null. Expected String");
        }
        
        if (car.getFuel() == null) {
            throw new IllegalArgumentException("Fuel of car cannot be null. Expected Fuel enum");
        }
        
        if (car.getTransmission()== null) {
            throw new IllegalArgumentException("Transmission of car cannot be null. Expected Transmission enum");
        }
    }
    
}