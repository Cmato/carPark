package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.dto.RentalCreateDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.facade.RentalFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.RentalService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xhubeny2
 */
@Service
@Transactional
public class RentalFacadeImpl implements RentalFacade{
    @Autowired
    BeanMappingService beanMappingSevice;
            
    @Autowired
    RentalService rentalService;
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    CarService carService;

    @Override
    public RentalDTO getRentalById(Long id) {
        return beanMappingSevice.mapTo(rentalService.getRentalById(id),
                RentalDTO.class);
    }

    @Override
    public void createRental(RentalCreateDTO r) {
        Employee employee = employeeService.findEmployeeById(
                r.getEmployee().getId());
        Car car = carService.getCarById(
                r.getCar().getId());
        Rental rental = new Rental(
                employee, car, r.getStartingDate(), r.getEstimatedReturnDate());
        rentalService.createRental(rental);
    }

    @Override
    public void deleteRental(Long id){
        rentalService.deleteRental(rentalService.getRentalById(id));
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
        return beanMappingSevice.mapTo(rentalService.getAllRentals(), 
                RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByEmployee(Long employeeId) {
        
        return beanMappingSevice.mapTo(rentalService.getRentalsByEmployee(
                employeeService.findEmployeeById(employeeId)), RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByState(RentalState state) {
        return beanMappingSevice.mapTo(rentalService.getRentalsByState(state),
                RentalDTO.class);
    }

    @Override
    public List<RentalDTO> getRentalsByCar(Long carId) {
        return beanMappingSevice.mapTo(rentalService.getRentalsByCar(
                carService.getCarById(carId)), RentalDTO.class);
    }
}
