/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.enums.ReservationState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.RentalFacade;
import cz.muni.fi.pa165.facade.ReservationFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dozer.MappingException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author xruzic16
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
    final static Logger log = LoggerFactory.getLogger(ReservationController.class);
    
    @Autowired
    private ReservationFacade reservationFacade;
    
    @Autowired
    private RentalFacade rentalFacade;

    @Autowired
    private EmployeeFacade emplFacade;
    @Autowired
    private CarFacade carFacade;
    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try{
            reservationFacade.removeReservation(id);
            log.debug("delete({})", id);
            redirectAttributes.addFlashAttribute("alert_success", "Reservation was deleted.");
        } catch (JpaSystemException ex) {
            redirectAttributes.addFlashAttribute("alert_error", "Can not delete. Reservation is blocked");
        }
            
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("reservations", reservationFacade.getAllReservations());
        return "reservation/list";
    }
    
    @RequestMapping(value = {"/detail/{id}", "/detail/"}, method = RequestMethod.GET)
    public String detail(@PathVariable Optional<Long> id, Model model) {

        if(!id.isPresent()) {
            model.addAttribute("reservation", new ReservationDTO());
        } else {
            model.addAttribute("reservation", reservationFacade.getReservationById(id.get()));
        }
        return "reservation/detail";
    }
    
    @ModelAttribute("employees")
    public List<EmployeeDTO> employees() {
        return emplFacade.findAllEmployees();
    }
    
    @ModelAttribute("cars")
    public List<CarDTO> cars() {
        return carFacade.getAllCars();
    }
    
    @ModelAttribute("state")
    public List<ReservationState> fuels() {
        return new ArrayList<>(EnumSet.allOf(ReservationState.class));
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("reservation") ReservationDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(reservation={})", formBean);
        
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create reservation
            try{
                formBean.setReservationState(ReservationState.ACTIVE);
                id = reservationFacade.createReservation(formBean);
            } catch (CarParkServiceException ex) {
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "Reservation was not created. " + ex.getMessage());
                return "redirect:" + uriBuilder.path("/reservation/detail/").toUriString();
            } catch (NullPointerException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/reservation/detail/").toUriString();
            }
        } else {
            //update reservation
            try{
                id = formBean.getId();
                reservationFacade.updateReservationCar(id, formBean.getCar());
                reservationFacade.updateReservationEmployee(id, formBean.getEmployee());
                reservationFacade.updateReservationStartingDate(id, formBean.getStartingDate());
                reservationFacade.updateReservationEndingDate(id, formBean.getEndingDate());
                //reservationFacade.updateReservationState(id, formBean.getReservationState());
                updateOrCreate = "updated";
            } catch (MappingException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/reservation/detail/{id}").buildAndExpand(id).encode().toUriString();
            }
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Reservation was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/reservation/list").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
    public String finish(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            reservationFacade.cancelReservation(id);
            redirectAttributes.addFlashAttribute("alert_success", "Reservation number " + id + " was canceled.");
        } catch (CarParkServiceException ex) {
            log.warn("Cannot finish rental {}",id);
            redirectAttributes.addFlashAttribute("alert_error", "Rental number " + id + " was not canceled. " + ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/reservation/list").toUriString();
    }
    
    @RequestMapping(value = "/rent/{id}", method = RequestMethod.POST)
    public String rent(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            rentalFacade.createRentalFromReservation(reservationFacade.getReservationById(id));
            redirectAttributes.addFlashAttribute("alert_success", "Rental created.");
        } catch (CarParkServiceException ex) {
            log.warn("Cannot create rental {}",id);
            redirectAttributes.addFlashAttribute("alert_error", "Rental number was not created. " + ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
}
