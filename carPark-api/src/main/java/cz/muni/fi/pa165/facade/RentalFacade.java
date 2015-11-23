package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.enums.RentalState;
import java.util.List;

/**
 *
 * @author xhubeny2
 */
public interface RentalFacade {
    public RentalDTO getRentalById(Long id);
    public void activateRental(Long id);
    public void reserveRental(Long id);
    public void cancelRental(Long id);
    public void finishRental(Long id);
    public List<RentalDTO> getAllRentals();
    public List<RentalDTO> getRentalsByEmployee(Long employeeId);
    public List<RentalDTO> getRentalsByState(RentalState state);
    
}
