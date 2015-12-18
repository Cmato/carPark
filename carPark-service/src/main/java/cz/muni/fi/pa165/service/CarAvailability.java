package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.ReservationState;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xhubeny2
 */
@Service
public class CarAvailability {
    

    @Autowired
    private RentalService rentalService;

    @Autowired
    private ReservationService reservationService;
     
    
    /**
     * Method checks if is possible to create new rental because of free car.
     * @param rental is rental to create
     * @return true if is possible to rent a car, otherwise false.
     */
    public boolean checkActualCarAvailability(Rental rental) {
        // check active and delayed rentals
        List<Rental> rentals = rentalService.getRentalsByState(RentalState.ACTIVE);
        rentals.addAll(rentalService.getRentalsByState(RentalState.DELAYED));
        for (Rental rental1 : rentals) {
            if (rental1.getCar().equals(rental.getCar())) {
                //if rental exist you can't rent car at all
                        return false;
            }
        }
        // check active reservations
        List<Reservation> reservations = reservationService.getReservationsByState(ReservationState.ACTIVE);
        for (Reservation res1 : reservations){
            if (res1.getCar().equals(rental.getCar())){
                if(!isPossibleToRent(rental.getStartingDate(),
                        rental.getEstimatedReturnDate(),
                        res1.getStartingDate(),
                        res1.getEndingDate()))
                    return false;
            }
        }
        return true;
    }
    
    /**
     * Method checks if is possible to create new reservation because of free car.
     * @param res is reservation to create 
     * @return true if is possible to rent a car, otherwise false.
     */
    public boolean checkActualCarAvailability(Reservation res) {
        // check active and delayed rentals
        List<Rental> rentals = rentalService.getRentalsByState(RentalState.ACTIVE);
        rentals.addAll(rentalService.getRentalsByState(RentalState.DELAYED));
        for (Rental rental1 : rentals) {
            if (rental1.getCar().equals(res.getCar())) {
                if(!isPossibleToRent(res.getStartingDate(),
                        res.getEndingDate(),
                        rental1.getStartingDate(),
                        rental1.getEstimatedReturnDate()))
                        return false;
            }
        }
        
        // check active reservations
        List<Reservation> reservations = reservationService.getReservationsByState(ReservationState.ACTIVE);
        for (Reservation res1 : reservations){
            if (res1.getCar().equals(res.getCar())){
                if(!isPossibleToRent(res.getStartingDate(),
                        res.getEndingDate(),
                        res1.getStartingDate(),
                        res1.getEndingDate()))
                    return false;
            }
        }
        
        return true;
    }
    /**
     * This method compares dates of new rental or reservation with existing
     * reservation. There are 3 wrong situations:
     * 1. when end of new event is between starting and ending dates of reservation
     * 2. when start of new event is between starting ans ending dates of reservation
     * 3. when start of new event is before start of existing res.,
     *      and end of new event if after end of existing res.
     * @param newStart - start of new event
     * @param newEnd - end of new event
     * @param exStart - start of existing reservation
     * @param exEnd - end of existing reservation
     * @return true if are the event not overlapping otherwise false
     */
    private static boolean isPossibleToRent(Date newStart, Date newEnd, Date exStart, Date exEnd){
        if (newEnd.compareTo(exStart) >= 0 && newEnd.compareTo(exEnd) <= 0){
            return false;
        }
        if (newStart.compareTo(exStart) >= 0 && newStart.compareTo(exEnd) <= 0){
            return false;
        }
        if (newStart.compareTo(exStart)<= 0 && newEnd.compareTo(exEnd)>= 0){
            return false;
        }
        return true;
    }
}
