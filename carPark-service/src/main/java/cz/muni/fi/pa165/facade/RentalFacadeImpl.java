package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.RentalService;
import cz.muni.fi.pa165.service.ReservationService;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author xhubeny2
 */
@Service
@Transactional
public class RentalFacadeImpl implements RentalFacade {
    @Inject
    private MappingService mappingService;
    
    @Inject
    RentalService rentalService;
    
    @Inject
    EmployeeService employeeService;
    
    @Inject
    CarService carService;
       
    @Inject
    ReservationService reservationService;

    @Override
    public Long createRental(RentalDTO r) {
        Rental mappedRental = mappingService.mapTo(r, Rental.class);
        return rentalService.createRental(mappedRental).getId();
    }
    
    @Override
    public Long createRentalFromReservation(ReservationDTO res){
        RentalDTO rent = new RentalDTO(res.getEmployee(), res.getCar(), res.getStartingDate(), res.getEndingDate());
        Long newRentalId = createRental(rent);
        reservationService.removeReservation(res.getId());
        return newRentalId;
    }

    @Override
    public boolean deleteRental(Long id){
        return rentalService.deleteRental(rentalService.getRentalById(id));
    }

    @Override
    public RentalDTO updateRentalEmployee(Long id, EmployeeDTO newEmployee) {
        Employee mappedEmployee = mappingService.mapTo(newEmployee, Employee.class);
        return mappingService.mapTo(rentalService.updateRentalEmployee(rentalService.getRentalById(id), mappedEmployee), RentalDTO.class);
    }

    @Override
    public RentalDTO updateRentalCar(Long id, CarDTO newCar) {
        Car mappedCar = mappingService.mapTo(newCar, Car.class);
        return mappingService.mapTo(rentalService.updateRentalCar(rentalService.getRentalById(id), mappedCar), RentalDTO.class);
    }

    @Override
    public RentalDTO updateRentalStartingDate(Long id, Date newDate) {
        return mappingService.mapTo(rentalService.updateRentalStartingDate(rentalService.getRentalById(id), newDate), RentalDTO.class);
    }

    @Override
    public RentalDTO updateRentalReturnDate(Long id, Date newReturnDate) {
        return mappingService.mapTo(rentalService.updateRentalReturnDate(rentalService.getRentalById(id), newReturnDate), RentalDTO.class);
    }

    @Override
    public RentalDTO updateRentalEstimatedReturnDate(Long id, Date newEstimatedReturnDate) {
        return mappingService.mapTo(rentalService.updateRentalEstimatedReturnDate(rentalService.getRentalById(id), newEstimatedReturnDate), RentalDTO.class);
    }

    @Override
    public RentalDTO updateRentalState(Long id, RentalState newRentalState) {
        return mappingService.mapTo(rentalService.updateRentalState(rentalService.getRentalById(id), newRentalState), RentalDTO.class);
    }

    @Override
    public RentalDTO getRentalById(Long id) {
        return mappingService.mapTo(rentalService.getRentalById(id), RentalDTO.class);
    }

    @Override
    public void delayRental(Long id) {
        rentalService.delayRental(rentalService.getRentalById(id));
    }

    @Override
    public void finishRental(Long id) {
        rentalService.finishRental(rentalService.getRentalById(id));
    }

    @Override
    public List<RentalDTO> getAllRentals() {
        return mappingService.mapToCollection(rentalService.getAllRentals(), 
                RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByEmployee(Long employeeId) {
        return mappingService.mapToCollection(rentalService.getRentalsByEmployee(
                employeeService.findEmployeeById(employeeId)), RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByState(RentalState state) {
        return mappingService.mapToCollection(rentalService.getRentalsByState(state),
                RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByCar(Long carId) {
        return mappingService.mapToCollection(rentalService.getRentalsByCar(
                carService.getCarById(carId)), RentalDTO.class);
    }

}
