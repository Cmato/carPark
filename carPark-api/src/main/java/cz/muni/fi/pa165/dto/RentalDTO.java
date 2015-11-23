package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.RentalState;
import java.util.Date;

/**
 *
 * @author xhubeny2
 */
public class RentalDTO {
    private Long id;

    private EmployeeDTO employee;

    private CarDTO car;

    private Date startingDate;

    private Date endingDate;
    
    private RentalState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
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
    
    public RentalState getState() {
        return state;
    }

    public void setState(RentalState state) {
        this.state = state;
    }
    
    @Override
    public int hashCode() {
        final int prime = 47;
	int result = 1;
        result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
	result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
	result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        //result = prime * result + car.hashCode() + employee.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
	if (obj == null)
            return false;
	if (! (obj instanceof RentalDTO))
            return false;
	RentalDTO other = (RentalDTO) obj;
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
	} else if (!car.equals(other.getCar()))
            return false;
        if (state != other.state)
            return false;
        return true;
	}
    
    /*@Override
    public String toString(){
        return "Rental " + id + " -- Employee: " + employee.getName() + ", Car: "
               + car.getName() + ", From: " + startingDate + " To: " + endingDate
               + ", State: " + state;
    }*/
}
