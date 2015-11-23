package cz.muni.fi.pa165.carPark;

import java.util.Date;

import cz.muni.fi.pa165.carPark.entities.Car;
import cz.muni.fi.pa165.carPark.entities.Employee;
import cz.muni.fi.pa165.carPark.entities.Rental;
import cz.muni.fi.pa165.carPark.entities.Reservation;
import cz.muni.fi.pa165.carPark.enums.Fuel;
import cz.muni.fi.pa165.carPark.enums.RentalState;
import cz.muni.fi.pa165.carPark.enums.Transmission;

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

	protected static Rental rental(Employee e, Car c, Date from, Date to, RentalState state) {
		Rental out = new Rental();
		out.setEmployee(e);
		out.setCar(c);
		out.setStartingDate(from);
		out.setEndingDate(to);
                out.setState(state);
		return out;
	}
        
        protected static Reservation reservation(Employee e, Car c, Date from, Date to) {
		Reservation out = new Reservation();
		out.setEmployee(e);
		out.setCar(c);
		out.setStartingDate(from);
		out.setEndingDate(to);
		return out;
	}

}
