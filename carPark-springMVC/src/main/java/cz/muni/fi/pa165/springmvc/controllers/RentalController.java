package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.enums.RentalState;
import cz.muni.fi.pa165.exceptions.CarParkServiceException;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.facade.RentalFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    public String list(Model model, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false) {
            model.addAttribute("rentals", rentalFacade.getRentalsByEmployee(user.getId()));
        } else {
            model.addAttribute("rentals", rentalFacade.getAllRentals());
        }
        
        return "rental/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false) {
            RentalDTO rent = rentalFacade.getRentalById(id);
            Long rentId = rent.getEmployee().getId();
            if(rentId != id)
                return "home/404";
        }
        
        RentalDTO rental = rentalFacade.getRentalById(id);
        rentalFacade.deleteRental(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Rental number: " + rental.getId() + " was deleted.");
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
    @RequestMapping(value = {"/detail/{id}", "/detail/"}, method = RequestMethod.GET)
    public String detail(@PathVariable Optional<Long> id, Model model, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(id.isPresent() && user.getIsAdmin() == false) {
            RentalDTO rent = rentalFacade.getRentalById(id.get());
            Long rentId = rent.getEmployee().getId();
            if(!rentId.equals((Long)id.get()))
                return "home/404";
        }

        if(!id.isPresent()) {
            model.addAttribute("rental", new RentalDTO());
        } else {
            model.addAttribute("rental", rentalFacade.getRentalById(id.get()));
        }
        return "rental/detail";
    }
    
    @ModelAttribute("employees")
    public List<EmployeeDTO> employees() {
        List<EmployeeDTO> employees = emplFacade.findAllEmployees();
        return employees;
    }
    
    @ModelAttribute("cars")
    public List<CarDTO> cars() {
        List<CarDTO> cars = carFacade.getAllCars();
        return cars;
    }
    
    @ModelAttribute("rentalStates")
    public List<RentalState> rentalStates() {
        List<RentalState> rentalStates = new ArrayList<>(EnumSet.allOf(RentalState.class));
        return rentalStates;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
         
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("rental") RentalDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(rental={})", formBean);
          
        Long id = null;
        String updateOrCreate = "created";
        if (formBean.getId() == null) {
            try {
                formBean.setRentalState(RentalState.ACTIVE);
                rentalFacade.createRental(formBean);
            } catch (CarParkServiceException ex) {
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "Rental was not created. " + ex.getMessage());
                return "redirect:" + uriBuilder.path("/rental/detail/").toUriString();
            } catch (NullPointerException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/rental/detail/").toUriString();
            }
        } else {
            //update car
            try{
                id = formBean.getId();
                rentalFacade.updateRentalCar(id, formBean.getCar());
                rentalFacade.updateRentalEmployee(id, formBean.getEmployee());
                rentalFacade.updateRentalStartingDate(id, formBean.getStartingDate());
                rentalFacade.updateRentalEstimatedReturnDate(id, formBean.getEstimatedReturnDate());
                updateOrCreate = "updated";
            } catch (MappingException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/rental/detail/{id}").buildAndExpand(id).encode().toUriString();
            }
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Reservation was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
    @RequestMapping(value = "/finish/{id}", method = RequestMethod.POST)
    public String finish(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false) {
            RentalDTO rent = rentalFacade.getRentalById(id);
            Long rentId = rent.getEmployee().getId();
            if(rentId != id)
                return "home/404";
        }
        
        try {
            rentalFacade.finishRental(id);
            redirectAttributes.addFlashAttribute("alert_success", "Rental number " + id + " was finished.");
        } catch (CarParkServiceException ex) {
            log.warn("Cannot finish rental {}",id);
            redirectAttributes.addFlashAttribute("alert_error", "Rental number " + id + " was not finished. " + ex.getMessage());
        }
        return "redirect:" + uriBuilder.path("/rental/list").toUriString();
    }
    
}
