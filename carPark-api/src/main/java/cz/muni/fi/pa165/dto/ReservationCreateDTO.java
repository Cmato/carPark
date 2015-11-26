package cz.muni.fi.pa165.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.enums.ReservationState;

public class ReservationCreateDTO {

    @NotNull
    private EmployeeDTO employee;

    @NotNull
    private CarDTO car;

    @NotNull
    private Date startingDate;

    @NotNull
    private Date endingDate;

    @NotNull
    private ReservationState reservationState;
    
    public ReservationCreateDTO(EmployeeDTO employee, CarDTO car, Date startingDate, Date endingDate) {
        this.employee = employee;
        this.car = car;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.reservationState = ReservationState.NEW;
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

    public ReservationState getReservationState() {
        return reservationState;
    }

    public void setReservationState(ReservationState state) {
        this.reservationState = state;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public int hashCode() {
        final int prime = 47;
        int result = 1;
        result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
        result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + ((reservationState == null) ? 0 : reservationState.hashCode());
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
        if (!(obj instanceof ReservationDTO)) {
            return false;
        }
        ReservationDTO other = (ReservationDTO) obj;
        if (startingDate == null) {
            if (other.getStartingDate() != null) {
                return false;
            }
        } else if (!startingDate.equals(other.getStartingDate())) {
            return false;
        }
        if (endingDate == null) {
            if (other.getEndingDate() != null) {
                return false;
            }
        } else if (!endingDate.equals(other.getEndingDate())) {
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
        if (reservationState != other.getReservationState()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservationCreateDTO{"
                + ", employee='" + employee + '\''
                + ", car='" + car + '\''
                + ", starting date= " + startingDate
                + ", ending date= " + endingDate
                + ", state= " + reservationState.toString()
                + '}';
    }
}
