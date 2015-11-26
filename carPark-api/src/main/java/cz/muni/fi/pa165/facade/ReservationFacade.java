package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.enums.ReservationState;

/**
 * 
 * @author xruzic16
 *
 */

public interface ReservationFacade {
	
	public ReservationDTO getReservationById(Long id);

    public void createReservation(ReservationCreateDTO r);
    
    public void acceptReservation(Long id);

    public void denyReservation(Long id);
    
    public void cancelReservation(Long id);
    
    public void doneReservation(Long id);

    public void removeReservation(Long id);

    public List<ReservationDTO> getAllReservations();

    public List<ReservationDTO> getReservationsByEmployee(Long employeeId);

    public List<ReservationDTO> getReservationsByState(ReservationState state);

    public List<ReservationDTO> getReservationsByCar(Long carId);

}
