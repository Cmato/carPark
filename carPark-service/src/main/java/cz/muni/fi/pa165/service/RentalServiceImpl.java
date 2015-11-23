package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.carPark.daos.RentalDao;
import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.entities.Rental;
import cz.muni.fi.pa165.carPark.enums.RentalState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * Implementation of the {@link RentalService}. This class is part of the service
 * module of the application that provides the implementation of the business
 * logic (main logic of the application).
 *
 * @author xhubeny2
 */
@Service
public class RentalServiceImpl implements RentalService{
    
    @Autowired
    private RentalDao rentalDao;

    public void createOrder(Rental rental) {
        rentalDao.create(rental);
    }

    public List<Rental> getRentalsByEmployee(Employee employee) {
        return rentalDao.findByEmployee(employee);
    }

    public List<Rental> getRentalByCar(Car car) {
        return rentalDao.findByCar(car);
    }

    public List<Rental> getRentalsByState(RentalState rentalState) {
        return rentalDao.findRentalsWithState(rentalState);
    }

    public List<Rental> getAllRentals() {
        return rentalDao.findAll();
    }

    public void finishRental(Rental rental) {
        checkTransition(rental.getRentalState(), RentalState.FINISHED);
        rental.setRentalState(RentalState.FINISHED);
    }

    public void delayRental(Rental rental) {
        checkTransition(rental.getRentalState(), RentalState.DELAYED);
        rental.setRentalState(RentalState.DELAYED);
    }

    public Rental getRentalById(Long id) {
        return rentalDao.findById(id);
    }
    
    /**
     * The only allowed changes of state are: ACTIVE - FINISHED,
     *                                        ACTIVE - DELAYED,
     *                                        DELAYED - FINISHED.
     */
    private Set<RentalStateTransition> allowedTransitions
            = new HashSet<RentalStateTransition>();
    {
        allowedTransitions.add(new RentalStateTransition(RentalState.ACTIVE,
                RentalState.FINISHED));
        allowedTransitions.add(new RentalStateTransition(RentalState.ACTIVE,
                RentalState.DELAYED));
        allowedTransitions.add(new RentalStateTransition(RentalState.DELAYED,
                RentalState.FINISHED));
    }
    
    private void checkTransition(RentalState oldState, RentalState newState) {
        RentalStateTransition rst
                = new RentalStateTransition(oldState, newState);
        if (!allowedTransitions.contains(rst)) {
            throw new CarParkServiceException("The transition from: " + oldState
                    + " to " + newState + " is not allowed!");
        }

    }
}
