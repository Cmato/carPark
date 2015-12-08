package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.Objects;
import javax.validation.constraints.Size;

/**
 *
 * @author xcmarko
 */
public class CarDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Fuel fuel;

    @NotNull
    private Transmission transmission;
    
    public CarDTO() {
    }

    public CarDTO(String name, String color, Fuel fuel, Transmission transmission) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
        this.transmission = transmission;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(getName());
        hash = 17 * hash + Objects.hashCode(getColor());
        hash = 17 * hash + Objects.hashCode(getFuel());
        hash = 17 * hash + Objects.hashCode(getTransmission());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarDTO other = (CarDTO) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getColor(), other.getColor())) {
            return false;
        }
        if (!Objects.equals(this.getFuel(), other.getFuel())) {
            return false;
        }
        if (!Objects.equals(this.getTransmission(), other.getTransmission())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", name=" + name + ", color=" + color + ", fuel=" + fuel + ", transmission=" + transmission + '}';
    }
}
