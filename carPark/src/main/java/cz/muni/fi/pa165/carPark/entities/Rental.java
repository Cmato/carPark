/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carPark.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @Column(nullable = false)
    @ManyToOne //mappedBy
    private Employee employee;
    
    @NotNull
    @Column(nullable = false)
    @ManyToOne //mappedBy
    private Car car;
    
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
        result = prime * result + car.hashCode() + employee.hashCode();
        return result;
    }

    
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
	if (obj == null)
            return false;
	if (! (obj instanceof Rental))
            return false;
	Rental other = (Rental) obj;
	if (name == null) {
            if (other.getName() != null)
                return false;
            } else if (!name.equals(other.getName()))
                return false;
	return true;
	}
    */
    
    @Override
    public String toString(){
        return "Rental " + id + " -- Employee: " + employee.getName() + " Car: "
               + car.getName() + " From: " + startingDate + " To: " + endingDate;
    }
}
