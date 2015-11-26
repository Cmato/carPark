package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Fuel fuel;

    @NotNull
    private Transmission transmission;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
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
        final CarCreateDTO other = (CarCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarCreateDTO{ name=" + name + ", color=" + color + ", fuel=" + fuel + ", transmission=" + transmission + '}';
    }

}
