package cz.muni.fi.pa165.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.daos.ReservationDao;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
    private ReservationDao reservationDao;

	@Override
	public Long createReservation(Reservation reservation) {
		if (!checkDates(reservation.getStartingDate(), reservation.getEndingDate())) {
            throw new CarParkServiceException("Reservation starting date is after"
                    + "estimated return date!");
        }
        //check active rentals and reservations
        CarAvailability ca = new CarAvailability();
       // ca.checkRentals(reservation);
        //ca.checkReservations(reservation);
        return reservationDao.create(reservation);
		
	}

	@Override
	public List<Reservation> getReservationsByEmployee(Employee employee) {
		return reservationDao.findByEmployee(employee);
	}

	@Override
	public List<Reservation> getReservationsByCar(Car car) {
		return reservationDao.findByCar(car);
	}

	@Override
	public List<Reservation> getReservationsByState(ReservationState reservationState) {
		return reservationDao.findByState(reservationState);
	}

	@Override
	public List<Reservation> getAllReservations() {
		return reservationDao.findAll();
	}

	@Override
	public Reservation getReservationById(Long id) {
		return reservationDao.findById(id);
	}

	@Override
	public void acceptReservation(Reservation reservation) {
		reservation.setReservationState(ReservationState.ACCEPTED);
		
	}

	@Override
	public void denyReservation(Reservation reservation) {
		reservation.setReservationState(ReservationState.DENIED);
		
	}

	@Override
	public void cancelReservation(Reservation reservation) {
		reservation.setReservationState(ReservationState.CANCELLED);
		
	}

	@Override
	public void completeReservation(Reservation reservation) {
		reservation.setReservationState(ReservationState.DONE);
		
	}

	@Override
	public void removeReservation(Reservation reservation) {
		reservation.setReservationState(ReservationState.REMOVED);
		
	}
	
	private boolean checkDates(Date starting, Date ending){
        return (starting.before(ending) || starting.equals(ending));
    }

}
