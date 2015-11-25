package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.daos.RentalDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Implementation of the {@link RentalService}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 *
 * @author xhubeny2
 */
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalDao rentalDao;

    @Override
    public void createRental(Rental rental) {
        checkDates(null, null);
        rentalDao.create(rental);
    }

    @Override
    public List<Rental> getRentalsByEmployee(Employee employee) {
        return rentalDao.findByEmployee(employee);
    }

    @Override
    public List<Rental> getRentalsByCar(Car car) {
        return rentalDao.findByCar(car);
    }

    @Override
    public List<Rental> getRentalsByState(RentalState rentalState) {
        return rentalDao.findRentalsWithState(rentalState);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalDao.findAll();
    }

    @Override
    public void finishRental(Rental rental) {
        checkTransition(rental.getRentalState(), RentalState.FINISHED);
        rental.setRentalState(RentalState.FINISHED);
        
        Calendar cal = Calendar.getInstance();
        rental.setReturnDate(cal.getTime());
    }

    @Override
    public void delayRental(Rental rental) {
        checkTransition(rental.getRentalState(), RentalState.DELAYED);
        rental.setRentalState(RentalState.DELAYED);
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalDao.findById(id);
    }
    
    @Override
    public void remove(Rental rental) {

    }

    /**
     * The only allowed changes of state are: ACTIVE -> FINISHED, ACTIVE ->
     * DELAYED, DELAYED -> FINISHED.
     */
    private final Set<RentalStateTransition> allowedTransitions
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
    
    private boolean checkDates(Date starting, Date ending){
        return !starting.after(ending);
    }
}
