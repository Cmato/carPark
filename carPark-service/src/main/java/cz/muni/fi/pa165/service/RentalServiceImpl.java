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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger log = LoggerFactory.getLogger(CarAvailability.class);
    
    @Autowired
    private RentalDao rentalDao;
    
    @Autowired
    private CarAvailability ca;

    @Override
    public Rental createRental(Rental rental) {
        if (!checkDates(rental.getStartingDate(), rental.getEstimatedReturnDate())) {
            throw new CarParkServiceException("The starting date is after"
                    + "estimated return date!");
        }
        //TODO opravit testy
        if (ca.checkActualCarAvailability(rental)){
            log.error("Can't create rental because the car is not available.");
            return null;
        }

        if(rentalDao.create(rental)) {
            return rental;
        }
        return null;
    }

    @Override
    public Rental updateRentalEmployee(Rental rental, Employee newEmployee) {
        if(rental != null && newEmployee != null) {
            rental.setEmployee(newEmployee);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public Rental updateRentalCar(Rental rental, Car newCar) {
        if(rental != null && newCar != null) {
            rental.setCar(newCar);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public Rental updateRentalStartingDate(Rental rental, Date newDate) {
        if(rental != null && newDate != null) {
            rental.setStartingDate(newDate);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public Rental updateRentalReturnDate(Rental rental, Date newReturnDate) {
        if(rental != null && newReturnDate != null) {
            rental.setReturnDate(newReturnDate);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public Rental updateRentalEstimatedReturnDate(Rental rental, Date newEstimatedReturnDate) {
        if(rental != null && newEstimatedReturnDate != null) {
            rental.setEstimatedReturnDate(newEstimatedReturnDate);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public Rental updateRentalState(Rental rental, RentalState newRentalState) {
        if(rental != null && newRentalState != null) {
            rental.setRentalState(newRentalState);
            return rentalDao.update(rental);
        }
        return null;
    }

    @Override
    public List<Rental> getRentalsByEmployee(Employee employee) {
        List<Rental> rentals = rentalDao.findByEmployee(employee);
        checkDelayedRentals(rentals);
        return rentals;
    }

    @Override
    public List<Rental> getRentalsByCar(Car car) {
        List<Rental> rentals = rentalDao.findByCar(car);
        checkDelayedRentals(rentals);
        return rentals;
        
    }

    @Override
    public List<Rental> getRentalsByState(RentalState rentalState) {
        checkDelayedRentals(rentalDao.findAll());
        return rentalDao.findRentalsWithState(rentalState);
    }

    @Override
    public List<Rental> getAllRentals() {
        List<Rental> rentals = rentalDao.findAll();
        checkDelayedRentals(rentals);
        return rentals;
    }

    private void checkDelayedRentals(List<Rental> rentals) {
        for (Rental rental : rentals) {
            if (rental.getRentalState() == RentalState.ACTIVE
                    && Calendar.getInstance().getTime().after(rental.getEstimatedReturnDate())){
                rental.setRentalState(RentalState.DELAYED);
                log.warn(rental.toString() + " is delayed!");
            }
        }
    }

    @Override
    public void finishRental(Rental rental) {
        checkTransition(rental.getRentalState(), RentalState.FINISHED);
        rental.setRentalState(RentalState.FINISHED);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
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
    public boolean deleteRental(Rental rental) {
        return rentalDao.remove(rental);
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
        return (starting.before(ending) || starting.equals(ending));
    }

}
