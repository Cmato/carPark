/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.facade.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xruzic16
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationFacade reservationFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("reservations", reservationFacade.getAllReservations());
        return "reservation/list";
    }
    
}