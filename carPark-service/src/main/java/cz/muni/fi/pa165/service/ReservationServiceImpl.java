package cz.muni.fi.pa165.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger log = LoggerFactory.getLogger(CarAvailability.class);

    @Autowired
    private CarAvailability ca;

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Reservation createReservation(Reservation reservation) {
        if (!checkDates(reservation.getStartingDate(), reservation.getEndingDate())) {
            throw new CarParkServiceException("Reservation starting date is after" + " estimated return date!");
        }
        // check active rentals and reservations
        if (!ca.checkActualCarAvailability(reservation)) {
            log.error("Can't create reservation because the car is not available.");
            return null;
        }

        if (reservationDao.create(reservation)) {
            return reservation;
        }
        return null;

    }

    @Override
    public Reservation updateReservationEmployee(Reservation reservation, Employee newEmployee) {
        if (reservation != null && newEmployee != null) {
            reservation.setEmployee(newEmployee);
            return reservationDao.update(reservation);
        }
        return null;
    }

    @Override
    public Reservation updateReservationCar(Reservation reservation, Car newCar) {
        if (reservation != null && newCar != null) {
            reservation.setCar(newCar);
            return reservationDao.update(reservation);
        }
        return null;
    }

    @Override
    public Reservation updateReservationStartingDate(Reservation reservation, Date newDate) {
        if (reservation != null && newDate != null) {
            reservation.setStartingDate(newDate);
            return reservationDao.update(reservation);
        }
        return null;
    }

    @Override
    public Reservation updateReservationEndingDate(Reservation reservation, Date newDate) {
        if (reservation != null && newDate != null) {
            reservation.setEndingDate(newDate);
            return reservationDao.update(reservation);
        }
        return null;
    }

    @Override
    public Reservation updateReservationState(Reservation reservation, ReservationState newState) {
        if (reservation != null && newState != null) {
            reservation.setReservationState(newState);
            return reservationDao.update(reservation);
        }
        return null;
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
    public void cancelReservation(Reservation reservation) {
        reservation.setReservationState(ReservationState.CANCELLED);

    }
    
    @Override
    public void removeReservation(Reservation reservation) {
        reservationDao.remove(reservation);

    }
    
    @Override
    public void removeReservation(Long id) {
        reservationDao.remove(reservationDao.findById(id));

    }

    private boolean checkDates(Date starting, Date ending) {
        return (starting.before(ending) || starting.equals(ending));
    }

}
