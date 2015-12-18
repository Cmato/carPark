/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.RentalFacade;
import cz.muni.fi.pa165.facade.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xcmarko
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CarFacade carFacade;
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @Autowired
    private ReservationFacade reservationFacade;
    
    @Autowired
    private RentalFacade rentalFacade;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("carsNumber", carFacade.getAllCars().size());
        model.addAttribute("employeeNumber", employeeFacade.findAllEmployees().size());
        model.addAttribute("reservationNumber", reservationFacade.getAllReservations().size());
        model.addAttribute("rentalNumber", rentalFacade.getAllRentals().size());
        return "home/index";
    }
}