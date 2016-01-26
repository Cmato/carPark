package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.ReservationService;
import java.util.Date;

/**
*
* @author xruzic16
*/
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade {

    @Inject
    private MappingService mappingService;

	@Inject
    private ReservationService reservationService;
	
	@Inject
    private EmployeeService employeeService;
	
	@Inject
    private CarService carService;

	@Override
	public Long createReservation(ReservationDTO r) {
	    
		Reservation mappedReservation = mappingService.mapTo(r, Reservation.class);      
        return reservationService.createReservation(mappedReservation).getId();		
	}

	@Override
    public ReservationDTO updateReservationEmployee(Long id, EmployeeDTO newEmployee) {
        Employee mappedEmployee = mappingService.mapTo(newEmployee, Employee.class);
        return mappingService.mapTo(reservationService.updateReservationEmployee(reservationService.getReservationById(id), mappedEmployee), ReservationDTO.class);
    }
	
	@Override
    public ReservationDTO updateReservationEmployeeById(Long id, Long idEmployee) {
        Employee mappedEmployee = employeeService.findEmployeeById(idEmployee);
        return mappingService.mapTo(reservationService.updateReservationEmployee(reservationService.getReservationById(id), mappedEmployee), ReservationDTO.class);
    }

	@Override
    public ReservationDTO updateReservationCar(Long id, CarDTO newCar) {
        Car mappedCar = mappingService.mapTo(newCar, Car.class);
        return mappingService.mapTo(reservationService.updateReservationCar(reservationService.getReservationById(id), mappedCar), ReservationDTO.class);
    }
	
	@Override
    public ReservationDTO updateReservationCarById(Long id, Long newCar) {
        Car mappedCar = carService.getCarById(newCar);
        return mappingService.mapTo(reservationService.updateReservationCar(reservationService.getReservationById(id), mappedCar), ReservationDTO.class);
    }

    @Override
    public ReservationDTO updateReservationStartingDate(Long id, Date newDate) {
        return mappingService.mapTo(reservationService.updateReservationStartingDate(reservationService.getReservationById(id), newDate), ReservationDTO.class);
    }

    @Override
    public ReservationDTO updateReservationEndingDate(Long id, Date newDate) {
        return mappingService.mapTo(reservationService.updateReservationEndingDate(reservationService.getReservationById(id), newDate), ReservationDTO.class);
    }

    @Override
    public ReservationDTO updateReservationState(Long id, ReservationState newState) {
        return mappingService.mapTo(reservationService.updateReservationState(reservationService.getReservationById(id), newState), ReservationDTO.class);
        
    }

    @Override
	public ReservationDTO getReservationById(Long id) {
		return mappingService.mapTo(reservationService.getReservationById(id), 
				ReservationDTO.class);
	}

	@Override
	public void cancelReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.CANCELLED);
		
	}
	
	@Override
    public void removeReservation(Long id) {
        reservationService.removeReservation(id);
        
    }

	@Override
	public List<ReservationDTO> getAllReservations() {
		return mappingService.mapToCollection(reservationService.getAllReservations(), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByEmployee(Long employeeId) {
		return mappingService.mapToCollection(reservationService.getReservationsByEmployee(employeeService.findEmployeeById(employeeId)), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByState(ReservationState state) {
		return mappingService.mapToCollection(reservationService.getReservationsByState(state), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByCar(Long carId) {
		return mappingService.mapToCollection(reservationService.getReservationsByCar(carService.getCarById(carId)), 
                ReservationDTO.class);
	}

}
