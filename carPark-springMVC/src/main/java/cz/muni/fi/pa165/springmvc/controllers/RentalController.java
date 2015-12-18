package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.RentalFacade;
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
    @Autowired
    private EmployeeFacade emplFacade;
    @Autowired
    private CarFacade carFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rentals", rentalFacade.getAllRentals());
        return "rental/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        RentalDTO rental = rentalFacade.getRentalById(id);
        rentalFacade.deleteRental(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Rental number: " + rental.getId() + " was deleted.");
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
    @RequestMapping(value = {"/detail/{id}", "/detail/"}, method = RequestMethod.GET)
    public String detail(@PathVariable Optional<Long> id, Model model) {

        if(!id.isPresent()) {
            model.addAttribute("rental", new RentalDTO());
        } else {
            model.addAttribute("rental", rentalFacade.getRentalById(id.get()));
        }
        return "rental/detail";
    }
    
    @ModelAttribute("employees")
    public List<String> employees() {
        List<String> names = new ArrayList<>();
        for(EmployeeDTO empl: emplFacade.findAllEmployees())
            names.add(empl.getName());
        return names;
    }
    
    @ModelAttribute("cars")
    public List<String> cars() {
        List<String> cars = new ArrayList<>();
        for(CarDTO car: carFacade.getAllCars())
            cars.add(car.getName());
        return cars;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("rental") RentalDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(rental={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "rental/list";
        }
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create car
            id = rentalFacade.createRental(formBean);
        } else {
            //update car
            id = formBean.getId();
            rentalFacade.updateRentalCar(id, formBean.getCar());
            rentalFacade.updateRentalEmployee(id, formBean.getEmployee());
            rentalFacade.updateRentalStartingDate(id, formBean.getStartingDate());
            rentalFacade.updateRentalReturnDate(id, formBean.getReturnDate());
            rentalFacade.updateRentalEstimatedReturnDate(id, formBean.getEstimatedReturnDate());
            rentalFacade.updateRentalState(id, formBean.getRentalState());
            updateOrCreate = "updated";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Reservation was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/rental/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
