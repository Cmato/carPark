package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
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
     
    //List<Rental> rentals = new ArrayList<>();
    
    /**
     * Method for checking if is selected car available for new rental.
     * @param rental 
     * @return true if is possible to rent a car, otherwise false
     */
    public boolean checkActualCarAvailability(Rental rental) {
        // check active rentals
        List<Rental> rentals = rentalService.getRentalsByState(RentalState.ACTIVE);
        rentals.addAll(rentalService.getRentalsByState(RentalState.DELAYED));
        for (Rental rental1 : rentals) {
            if (rental1.getCar().equals(rental.getCar())) {
                return false;
            }
        }
        // check active reservations
        List<Reservation> reservations = reservationService.getReservationsByState(ReservationState.ACCEPTED);
        for (Reservation reservation1 : reservations){
            if (reservation1.getCar().equals(rental.getCar())){
                // TODO
                return false;
            }
        }
        return true;
    }

    /*public boolean checkReservations(Rental rental) {
        List<Reservation> reservations = reservationService.getReservationsByState(ReservationState.ACCEPTED);
        reservations.addAll(reservationService.getReservationsByState(ReservationState.NEW));
         for (Reservation reservation1 : reservations) {
        	 if (reservation1.getCar().equals(rental.getCar()))
        		 throw new CarParkServiceException("The car is already reserved.");
         }
    }
    
    public boolean checkReservations(Reservation reservation) {
        List<Reservation> reservations = reservationService.getReservationsByState(ReservationState.ACCEPTED);
        reservations.addAll(reservationService.getReservationsByState(ReservationState.NEW));
         for (Reservation reservation1 : reservations) {
        	 if (reservation1.getCar().equals(reservation.getCar()))
        		 throw new CarParkServiceException("The car is already reserved.");
         }
    }*/

}
