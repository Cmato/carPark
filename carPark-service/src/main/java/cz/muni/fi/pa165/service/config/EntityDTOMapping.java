package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 *
 * @author xcmarko
 */
public class EntityDTOMapping extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Car.class, CarDTO.class);
        mapping(Employee.class, EmployeeDTO.class);
        mapping(Rental.class, RentalDTO.class);
        mapping(Reservation.class, ReservationDTO.class);
    }

}