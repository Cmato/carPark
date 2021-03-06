package cz.muni.fi.pa165.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.utils.DateFormater;

/**
 *
 * @author xruzic16
 */
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(nullable = false)
	private Employee employee;

	@NotNull
	@ManyToOne(targetEntity = Car.class)
	@JoinColumn(nullable = false)
	private Car car;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startingDate;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endingDate;
	
	@Enumerated
	@NotNull
	private ReservationState reservationState;

	public Reservation() {
	}

	public Reservation(Employee employee, Car car, Date startingDate, Date endingDate) {
		this.employee = employee;
		this.car = car;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.reservationState = ReservationState.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ReservationState getReservationState() {
		return reservationState;
	}

	public void setReservationState(ReservationState reservationState) {
		this.reservationState = reservationState;
	}

	@Override
	public int hashCode() {
		final int prime = 127;
		int result = 7;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
		result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((reservationState == null) ? 0 : reservationState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reservation))
			return false;
		Reservation other = (Reservation) obj;
		if (startingDate == null) {
			if (other.getStartingDate() != null)
				return false;
		} else if (!startingDate.equals(other.getStartingDate()))
			return false;
		if (endingDate == null) {
			if (other.getEndingDate() != null)
				return false;
		} else if (!endingDate.equals(other.getEndingDate()))
			return false;
		if (employee == null) {
			if (other.getEmployee() != null)
				return false;
		} else if (!this.getEmployee().equals(other.getEmployee()))
			return false;
		if (car == null) {
			if (other.getCar() != null)
				return false;
		} else if (!this.getCar().equals(other.getCar()))
			return false;
		if (reservationState == null) {
			if (other.getReservationState() != null)
				return false;
		} else if (!this.getReservationState().equals(other.getReservationState()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reservation " + id + " -- Employee: " + employee.getName() + " Car: " + car.getName() +
				" From: " + DateFormater.format(startingDate) + " To: " + DateFormater.format(endingDate)+
				" State: " + reservationState.toString();
	}
}