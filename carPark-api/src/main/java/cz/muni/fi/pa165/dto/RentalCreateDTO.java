package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.RentalState;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xhubeny2
 */
public class RentalCreateDTO {

    @NotNull
    private EmployeeDTO employee;

    @NotNull
    private CarDTO car;

    @NotNull
    private Date startingDate;

    @NotNull
    private Date estimatedReturnDate;

    private Date returnDate;

    @NotNull
    private RentalState rentalState;

    public RentalCreateDTO(EmployeeDTO employee, CarDTO car, Date startingDate, Date estimatedReturnDate) {
        this.employee = employee;
        this.car = car;
        this.startingDate = startingDate;
        this.estimatedReturnDate = estimatedReturnDate;
        this.returnDate = null;
        this.rentalState = RentalState.ACTIVE;
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

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public RentalState getRentalState() {
        return rentalState;
    }

    public void setRentalState(RentalState rentalState) {
        this.rentalState = rentalState;
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
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RentalCreateDTO)) {
            return false;
        }
        RentalCreateDTO other = (RentalCreateDTO) obj;
        if (startingDate == null) {
            if (other.getStartingDate() != null) {
                return false;
            }
        } else if (!startingDate.equals(other.getStartingDate())) {
            return false;
        }
        if (estimatedReturnDate == null) {
            if (other.getEstimatedReturnDate() != null) {
                return false;
            }
        } else if (!estimatedReturnDate.equals(other.getEstimatedReturnDate())) {
            return false;
        }
        if (employee == null) {
            if (other.getEmployee() != null) {
                return false;
            }
        } else if (!this.getEmployee().equals(other.getEmployee())) {
            return false;
        }
        if (car == null) {
            if (other.getCar() != null) {
                return false;
            }
        } else if (!car.equals(other.getCar())) {
            return false;
        }
        if (rentalState != other.rentalState) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RentalCreateDTO{"
                + ", employee='" + employee + '\''
                + ", car='" + car + '\''
                + ", starting date=" + startingDate
                + ", estimated return date=" + estimatedReturnDate
                + ", state=" + rentalState.toString()
                + '}';
    }
}
