package cz.muni.fi.pa165.carPark.entities;

import cz.muni.fi.pa165.carPark.enums.RentalState;
import java.util.Date;

import javax.persistence.CascadeType;
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

/**
 *
 * @author xhubeny2
 */

@Entity
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @ManyToOne(targetEntity=Employee.class)
    @JoinColumn(nullable = false)
    private Employee employee;
    
    @NotNull
    @ManyToOne(targetEntity=Car.class)
    @JoinColumn(nullable = false)
    private Car car;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date estimatedReturnDate;
    
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    
    @Enumerated
    @NotNull
    private RentalState rentalState;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    } 

    public RentalState getRentalState() {
        return rentalState;
    }

    public void setRentalState(RentalState rentalState) {
        this.rentalState = rentalState;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    @Override
    public int hashCode() {
        final int prime = 47;
	int result = 1;
        result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
	result = prime * result + ((estimatedReturnDate == null) ? 0 : estimatedReturnDate.hashCode());
	result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + ((rentalState == null) ? 0 : rentalState.hashCode());
        //result = prime * result + car.hashCode() + employee.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
	if (obj == null)
            return false;
	if (! (obj instanceof Rental))
            return false;
	Rental other = (Rental) obj;
        if (startingDate == null) {
			if (other.getStartingDate() != null)
				return false;
		} else if (!startingDate.equals(other.getStartingDate()))
			return false;
        if (estimatedReturnDate == null) {
			if (other.getEstimatedReturnDate() != null)
				return false;
		} else if (!estimatedReturnDate.equals(other.getEstimatedReturnDate()))
			return false;
        if (employee == null) {
            if (other.getEmployee() != null)
		return false;
	} else if (!this.getEmployee().equals(other.getEmployee()))
            return false;
        if (car == null) {
            if (other.getCar() != null)
		return false;
	} else if (!car.equals(other.getCar()))
            return false;
        if (rentalState != other.rentalState)
            return false;
        return true;
	}
    
    @Override
    public String toString(){
        return "Rental " + id + " -- Employee: " + employee.getName() + ", Car: "
               + car.getName() + ", From: " + startingDate + " To: " + estimatedReturnDate
               + ", State: " + rentalState;
    }
}
