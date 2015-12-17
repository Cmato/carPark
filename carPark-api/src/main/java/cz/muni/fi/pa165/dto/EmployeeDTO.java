package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Size;

/**
 *
 * @author xhasprun
 */
public class EmployeeDTO {
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    
    @NotNull
    private Date birth;
    
    @NotNull
    @Size(min = 8, max = 10)
    private String idCardNumber;
    
    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, Date birth, String idCardNumber) {
        this.name = name;
        this.birth = birth;
        this.idCardNumber = idCardNumber;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeDTO other = (EmployeeDTO) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getIdCardNumber(), other.getIdCardNumber())) {
            return false;
        }
        if (!Objects.equals(this.getBirth(), other.getBirth())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(getName());
        hash = 17 * hash + Objects.hashCode(getIdCardNumber());
        hash = 17 * hash + Objects.hashCode(getBirth());

        return hash;
    }
}
