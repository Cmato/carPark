package cz.muni.fi.pa165.carPark.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.carPark.utils.DateFormater;

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
	@ManyToOne
	@JoinColumn(nullable = false)
	private Employee employee;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startingDate;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endingDate;

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

	@Override
	public int hashCode() {
		final int prime = 127;
		int result = 7;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
		result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rental))
			return false;
		Rental other = (Rental) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "Rental " + id + " -- Employee: " + employee.getName() + " From: " + DateFormater.format(startingDate) + " To: " + DateFormater.format(endingDate);
	}
}
