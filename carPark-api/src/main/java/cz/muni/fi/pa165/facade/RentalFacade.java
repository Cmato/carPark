package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.Date;
import java.util.List;

/**
 *
 * @author xhubeny2
 */
public interface RentalFacade {

    public Long createRental(RentalDTO r);
    
    public boolean deleteRental(Long id);

    public RentalDTO updateRentalEmployee(Long id, EmployeeDTO newEmployee);
    
    public RentalDTO updateRentalCar(Long id, CarDTO newCar);

    public RentalDTO updateRentalStartingDate(Long id, Date newDate);

    public RentalDTO updateRentalReturnDate(Long id, Date newReturnDate);

    public RentalDTO updateRentalEstimatedReturnDate(Long id, Date newEstimatedReturnDate);

    public RentalDTO updateRentalState(Long id, RentalState newRentalState);

    public RentalDTO getRentalById(Long id);

    public void delayRental(Long id);

    public void finishRental(Long id);

    public List<RentalDTO> getAllRentals();

    public List<RentalDTO> getRentalsByEmployee(Long employeeId);

    public List<RentalDTO> getRentalsByState(RentalState state);

    public List<RentalDTO> getRentalsByCar(Long carId);

}
