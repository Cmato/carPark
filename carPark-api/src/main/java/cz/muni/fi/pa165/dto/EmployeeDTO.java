/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author xhasprun
 */
public class EmployeeDTO {
    private Long id;
    
    private String name;
    
    private Date birth;
    
    private String idCardNumber;
    
    List<ReservationDTO> reservations;
    
    List<RentalDTO> rentals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public List<RentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EmployeeDTO))
			return false;
		EmployeeDTO second = (EmployeeDTO) obj;
                //name comparison
		if (name == null) {
			if (second.getName() != null)
				return false;
		} else if (!name.equals(second.getName()))
			return false;
                //birth date comparison
                if (birth == null) {
			if (second.getBirth()!= null)
				return false;
		} else if (!birth.equals(second.getBirth()))
			return false;
                
		return true;
    }
    
    @Override
    public int hashCode(){
        //conflict not expected -> might change in future
        final int prime = 37;
	int hash = 1;
	hash = prime * hash + ((name == null) ? 0 : name.hashCode());
	hash = prime * hash + ((birth == null) ? 0 : birth.hashCode());
	return hash;
    }
    
    
}
