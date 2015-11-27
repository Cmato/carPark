package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.dto.ReservationCreateDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Car;
import cz.muni.fi.pa165.entities.Employee;
import cz.muni.fi.pa165.entities.Reservation;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CarService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.ReservationService;

/**
*
* @author xruzic16
*/
@Service
@Transactional
public class ReservationFacadeImpl implements ReservationFacade {
	
	@Inject
    ReservationService reservationService;
	
	@Inject
    EmployeeService employeeService;
	
	@Inject
    CarService carService;

	
    @Autowired
    BeanMappingService beanMappingSevice;

	@Override
	public ReservationDTO getReservationById(Long id) {
		return beanMappingSevice.mapTo(reservationService.getReservationById(id), 
				ReservationDTO.class);
	}

	@Override
	public Long createReservation(ReservationCreateDTO r) {
		Reservation res = new Reservation();
		res.setCar(beanMappingSevice.mapTo(r.getCar(), Car.class));
		res.setEmployee(beanMappingSevice.mapTo(r.getEmployee(), Employee.class));
		res.setStartingDate(r.getStartingDate());
		res.setEndingDate(r.getEndingDate());
		res.setReservationState(r.getReservationState());
		return reservationService.createReservation(res);
		
	}

	@Override
	public void acceptReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.ACCEPTED);
		
	}

	@Override
	public void denyReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.DENIED);
		
	}

	@Override
	public void cancelReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.CANCELLED);
		
	}

	@Override
	public void doneReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.DONE);
		
	}

	@Override
	public void removeReservation(Long id) {
		reservationService.getReservationById(id).setReservationState(ReservationState.REMOVED);
		
	}

	@Override
	public List<ReservationDTO> getAllReservations() {
		return beanMappingSevice.mapTo(reservationService.getAllReservations(), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByEmployee(Long employeeId) {
		return beanMappingSevice.mapTo(reservationService.getReservationsByEmployee(employeeService.findEmployeeById(employeeId)), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByState(ReservationState state) {
		return beanMappingSevice.mapTo(reservationService.getReservationsByState(state), 
                ReservationDTO.class);
	}

	@Override
	public List<ReservationDTO> getReservationsByCar(Long carId) {
		return beanMappingSevice.mapTo(reservationService.getReservationsByCar(carService.getCarById(carId)), 
                ReservationDTO.class);
	}

}
