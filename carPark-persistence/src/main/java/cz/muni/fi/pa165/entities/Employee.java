package cz.muni.fi.pa165.entities;

import java.util.Date;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Column(unique = true)
    private String email;
    
    @NotNull
    @Column(nullable = false)
    private String password;
    
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;
    
    @NotNull
    @Column(unique = true)
    private String idCardNumber;
    
    @NotNull
    private boolean isAdmin;

    public Employee() {
    }

    public Employee(String name, Date birth, String idCardNumber, String email, String password) {
        this.name = name;
        this.birth = birth;
        this.idCardNumber = idCardNumber;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (!Objects.equals(this.birth, other.getBirth())) {
            return false;
        }
        if (!Objects.equals(this.idCardNumber, other.getIdCardNumber())) {
            return false;
        }
        if (!Objects.equals(this.email, other.getEmail())) {
            return false;
        }
        if (!Objects.equals(this.password, other.getPassword())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + ((name == null) ? 0 : name.hashCode());
        hash = prime * hash + ((birth == null) ? 0 : birth.hashCode());
        return hash;
    }

}