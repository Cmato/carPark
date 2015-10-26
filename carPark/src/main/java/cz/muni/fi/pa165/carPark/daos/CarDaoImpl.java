/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author xcmarko
 */
public class CarDaoImpl implements CarDao{
    
    private EntityManagerFactory emf;
    
    public CarDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void createCar(Car car) {
        validateCar(car);
        if(car.getId() != null) {
            throw new IllegalArgumentException("Cannot create car with assigned id");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCar(Car car) {
        if (car == null) {
            throw new NullPointerException("Car is null. Nothing to delete");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Cannot delete car with null id. Nothing to delete");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Car toRemove = em.merge(car);
        em.remove(toRemove);
        em.getTransaction().commit();
    }

    @Override
    public void updateCar(Car car) {
        validateCar(car);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

    @Override
    public Car findCarById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Car> findAllCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
