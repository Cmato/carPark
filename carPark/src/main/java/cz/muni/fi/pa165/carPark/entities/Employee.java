/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carPark.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Date birth;
    
    private String idCardNumber;
    
    //@OneToMany(mappedBy="emlpoyee")
    //List<Reservation> reservations;
    
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
        //TODO
        return true;
    }
    
    @Override
    public int hashCode(){
        //TODO
        return 1;
    }
    
    
}
