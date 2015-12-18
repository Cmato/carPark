/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.ReservationFacade;

/**
 *
 * @author xruzic16
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
    final static Logger log = LoggerFactory.getLogger(CarController.class);
    
    @Autowired
    private ReservationFacade reservationFacade;
    @Autowired
    private EmployeeFacade emplFacade;
    @Autowired
    private CarFacade carFacade;
    
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
    public List<String> employees() {
        List<String> names = new ArrayList<String>();
        for(EmployeeDTO e: emplFacade.findAllEmployees())
            names.add(e.getName());
        return names;
    }
    
    @ModelAttribute("cars")
    public List<String> cars() {
        List<String> cars = new ArrayList<String>();
        for(CarDTO e: carFacade.getAllCars())
            cars.add(e.getName());
        return cars;
    }
    
    /**
     * Spring Validator added to JSR-303 Validator for this @Controller only.
     * It is useful  for custom validations that are not defined on the form bean by annotations.
     * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
     */
   /* @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CarDTO) {
            binder.addValidators(new ReservationCreateDTOValidator());
        }
    }*/
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("reservation") ReservationDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(reservation={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "reservation/new";
        }
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create car
            id = reservationFacade.createReservation(formBean);
        } else {
            //update car
            id = formBean.getId();
            reservationFacade.updateReservationCar(id, formBean.getCar());
            reservationFacade.updateReservationEmployee(id, formBean.getEmployee());
            reservationFacade.updateReservationStartingDate(id, formBean.getStartingDate());
            reservationFacade.updateReservationEndingDate(id, formBean.getEndingDate());
            updateOrCreate = "updated";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Reservation was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/reservation/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
