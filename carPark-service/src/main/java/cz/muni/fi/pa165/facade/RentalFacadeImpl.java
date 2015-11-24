package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RentalService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xhubeny2
 */
public class RentalFacadeImpl implements RentalFacade{
    @Autowired
    BeanMappingService beanMappingSevice;
            
    @Autowired
    RentalService rentalService;
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    CarService carService;

    public RentalDTO getRentalById(Long id) {
        return beanMappingSevice.mapTo(rentalService.getRentalById(id),
                RentalDTO.class);
    }

    public void createRental(RentalDTO r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delayRental(Long id) {
        rentalService.delayRental(rentalService.getRentalById(id));
    }

    public void finishRental(Long id) {
        rentalService.finishRental(rentalService.getRentalById(id));
    }

    public List<RentalDTO> getAllRentals() {
        return beanMappingSevice.mapTo(rentalService.getAllRentals(), 
                RentalDTO.class);
    }

    public List<RentalDTO> getRentalsByEmployee(Long employeeId) {
        
        return beanMappingSevice.mapTo(rentalService.getRentalsByEmployee(
                employeeService.getEmployeeById(employeeId)), RentalDTO.class);
    }

    public List<RentalDTO> getRentalsByState(RentalState state) {
        return beanMappingSevice.mapTo(rentalService.getRentalsByState(state),
                RentalDTO.class);
    }

    public List<RentalDTO> getRentalsByCar(Long carId) {
        return beanMappingSevice.mapTo(rentalService.getRentalsByCar(
                carService.getCarById(carId)), RentalDTO.class);
    }
}
