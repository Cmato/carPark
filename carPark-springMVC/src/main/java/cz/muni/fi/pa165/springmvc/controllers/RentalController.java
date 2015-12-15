package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.facade.RentalFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xhubeny2
 */
@Controller
@RequestMapping("/rental")
public class RentalController {
    
    final static Logger log = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalFacade rentalFacade;
    
    @RequestMapping(value = "/rental", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rentals", rentalFacade.getAllRentals());
        return "rental/list";
    }
    
}
