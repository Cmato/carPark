package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RentalCreateDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.List;

/**
 *
 * @author xhubeny2
 */
public interface RentalFacade {
    public RentalDTO getRentalById(Long id);
    public Long createRental(RentalCreateDTO r);
    public void delayRental(Long id);
    public void finishRental(Long id);
    public List<RentalDTO> getAllRentals();
    public List<RentalDTO> getRentalsByEmployee(Long employeeId);
    public List<RentalDTO> getRentalsByState(RentalState state);
    public List<RentalDTO> getRentalsByCar(Long carId);
    
}
