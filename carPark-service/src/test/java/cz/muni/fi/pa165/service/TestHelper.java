package cz.muni.fi.pa165.service;

import java.util.Date;

import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.enums.Transmission;

/*
 * 
 * @authors xruzic16
 */

public class TestHelper {

	public static Car car(String name, String color, Fuel fuel, Transmission trans) {
		Car out = new Car();
		out.setName(name);
		out.setColor(color);
		out.setFuel(fuel);
		out.setTransmission(trans);
		return out;
	}

	protected static Employee employee(String name, Date date, String idCardNumber) {
		Employee out = new Employee();
		out.setName(name);
		out.setBirth(date);
		out.setIdCardNumber(idCardNumber);
		return out;
	}

	protected static Rental rental(Employee e, Car c, Date from, Date to, RentalState rentalState) {
		Rental out = new Rental();
		out.setEmployee(e);
		out.setCar(c);
		out.setStartingDate(from);
		out.setEstimatedReturnDate(to);
                out.setRentalState(rentalState);
		return out;
	}
        
        protected static Reservation reservation(Employee e, Car c, Date from, Date to, ReservationState state) {
		Reservation out = new Reservation();
		out.setEmployee(e);
		out.setCar(c);
		out.setStartingDate(from);
		out.setEndingDate(to);
		out.setReservationState(state);
		return out;
	}

}
