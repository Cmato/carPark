package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.Objects;

/**
 *
 * @author xcmarko
 */
public class CarDTO {

    private Long id;

    private String name;

    private String color;

    private Fuel fuel;

    private Transmission transmission;

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
    
    public void setId(Long id) {
        this.id = id;
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", name=" + name + ", color=" + color + ", fuel=" + fuel + ", transmission=" + transmission + '}';
    }
}
