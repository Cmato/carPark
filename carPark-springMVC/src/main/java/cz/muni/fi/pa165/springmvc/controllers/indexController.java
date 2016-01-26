/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.RentalFacade;
import cz.muni.fi.pa165.facade.ReservationFacade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author xcmarko
 */
@Controller
@RequestMapping("/")
public class indexController {
    
    final static Logger log = LoggerFactory.getLogger(indexController.class);
    
    @Autowired
    private CarFacade carFacade;
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @Autowired
    private ReservationFacade reservationFacade;
    
    @Autowired
    private RentalFacade rentalFacade;
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false) {
            model.addAttribute("carsNumber", carFacade.getAllCars().size());
            model.addAttribute("employeeNumber", 0);
            model.addAttribute("reservationNumber", reservationFacade.getReservationsByEmployee(user.getId()).size());
            model.addAttribute("rentalNumber", rentalFacade.getRentalsByEmployee(user.getId()).size());
        } else {
            model.addAttribute("carsNumber", carFacade.getAllCars().size());
            model.addAttribute("employeeNumber", employeeFacade.findAllEmployees().size());
            model.addAttribute("reservationNumber", reservationFacade.getAllReservations().size());
            model.addAttribute("rentalNumber", rentalFacade.getAllRentals().size());
        }        
        
        return "home/home";
    }
    
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound(Model model) {
        return "home/404";
    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logingGet(Model model
    ) {
        
        model.addAttribute("authenticateUser", new EmployeeAuthenticateDTO());
        return "home/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid
            @ModelAttribute("authenticateUser") EmployeeAuthenticateDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request
    ) {
        log.debug("login(carPark={})", formBean);
        
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            return "home/login";
        }
        
        //
        if (!employeeFacade.authenticate(formBean)) {
            model.addAttribute("alert_warning", "You couldn't be logged in - wrong email or password.");
            return "home/login";
        }
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "You have been logged in as " + formBean.getEmail() + ".");
        request.getSession().setAttribute("authenticatedUser", employeeFacade.findEmployeeByEmail(formBean.getEmail()));
        log.debug("User logged in: " + formBean.getEmail());
        return "redirect:/";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        log.debug("logout");
        request.getSession().removeAttribute("authenticatedUser");
        model.addAttribute("alert_success", "You have been logged out.");
        return "home/login";
    }
}