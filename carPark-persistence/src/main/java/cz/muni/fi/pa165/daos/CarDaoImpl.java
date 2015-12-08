package cz.muni.fi.pa165.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;

/**
 *
 * @author xcmarko
 */
@Repository
@Transactional
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean createCar(Car car) {
        validateCar(car);
        if (em.contains(car)) {
            return false;
        }
        em.persist(car);
        em.flush();
        return true;
    }

    @Override
    public boolean deleteCar(Car car) {
        if (car == null) {
            throw new NullPointerException("Car is null. Nothing to delete");
        }
        validateCar(car);
        if (!em.contains(car)) {
            return false;
        }
        em.remove(em.merge(car));
        return true;
    }

    @Override
    public Car updateCar(Car car) {
        validateCar(car);
        return em.merge(car);
    }

    @Override
    public Car findCarById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Cannot find Car. Wrong id parameter given");
        }
        return em.find(Car.class, id);
    }

    @Override
    public List<Car> findAllCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }
    
    @Override
    public List<Car> findCarByFuel(Fuel fuel) {
        if (fuel == null) {
            throw new IllegalArgumentException("Cannot find Cars. Wrong fuel parameter given");
        }
        return em.createQuery("SELECT c FROM Car c WHERE c.fuel = :fuel", Car.class)
                .setParameter("fuel", fuel).getResultList();
    }

    @Override
    public List<Car> findCarByTransmission(Transmission transmission) {
        if (transmission == null) {
            throw new IllegalArgumentException("Cannot find Cars. Wrong transmission parameter given");
        }
        return em.createQuery("SELECT c FROM Car c WHERE c.transmission = :transmission", Car.class)
                .setParameter("transmission", transmission).getResultList();
    }
    
    private void validateCar(Car car) throws IllegalArgumentException, NullPointerException {
        if (car == null) {
            throw new NullPointerException("Given car is null");
        }

        if (car.getName() == null) {
            throw new IllegalArgumentException("Name of car cannot be null. Expected String");
        }
        
        if (car.getColor() == null) {
            throw new IllegalArgumentException("Color of car cannot be null. Expected String");
        }
        
        if (car.getFuel() == null) {
            throw new IllegalArgumentException("Fuel of car cannot be null. Expected Fuel enum");
        }
        
        if (car.getTransmission() == null) {
            throw new IllegalArgumentException("Transmission of car cannot be null. Expected Transmission enum");
        }
    }
    
}
