package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xhubeny2
 */
public class CarAvailability {
    
    @Autowired
    private RentalService rentalService;
    
    @Autowired
    private ReservationService reservationService;

    public CarAvailability(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public CarAvailability(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
      
    public void checkRentals(Rental rental){
        List<Rental> rentals = rentalService.getRentalsByState(RentalState.ACTIVE);
        rentals.addAll(rentalService.getRentalsByState(RentalState.DELAYED));
        for (Rental rental1 : rentals) {
            if (rental1.getCar().equals(rental.getCar()))
                throw new CarParkServiceException("The car is already rented.");
        }
    }
    
    public void checkReservations(Reservation reservation){
        /*List<Reservation> rentals = rentalService.getRentalsByState(RentalState.ACTIVE);
        rentals.addAll(rentalService.getRentalsByState(RentalState.DELAYED));
        for (Rental rental1 : rentals) {
            if (rental1.getCar().equals(rental.getCar()))
                throw new CarParkServiceException("The car is already reserved.");
        }*/
    }
    
}
