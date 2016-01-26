package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import java.util.List;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.enums.ReservationState;
import java.util.Date;

/**
 * 
 * @author xruzic16
 *
 */

public interface ReservationFacade {

    public Long createReservation(ReservationDTO r);

    //public boolean removeReservation(Long id);
	
	public ReservationDTO getReservationById(Long id);

    public ReservationDTO updateReservationEmployee(Long id, EmployeeDTO newEmployee);
    
    public ReservationDTO updateReservationCar(Long id, CarDTO newCar);
    
    public ReservationDTO updateReservationStartingDate(Long id, Date newDate);
    
    public ReservationDTO updateReservationEndingDate(Long id, Date newDate);

    public ReservationDTO updateReservationState(Long id, ReservationState newState);
    
    public void cancelReservation(Long id);
    
    public void removeReservation(Long id);

    public List<ReservationDTO> getAllReservations();

    public List<ReservationDTO> getReservationsByEmployee(Long employeeId);

    public List<ReservationDTO> getReservationsByState(ReservationState state);

    public List<ReservationDTO> getReservationsByCar(Long carId);

    ReservationDTO updateReservationEmployeeById(Long id, Long idEmployee);

    ReservationDTO updateReservationCarById(Long id, Long newCar);

}
