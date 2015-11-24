/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Erik Hasprunar
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false)
    private String name;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(nullable=false)
    private Date birth;
    
    @Column(unique=true)
    private String idCardNumber;
    
    @OneToMany(targetEntity=Reservation.class,mappedBy="employee")
    List<Reservation> reservations;
    
    @OneToMany(targetEntity=Rental.class,mappedBy="employee")
    List<Rental> rentals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Long getId() {
        return id;
    }

    public Date getBirth() {
        return birth;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee second = (Employee) obj;
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
