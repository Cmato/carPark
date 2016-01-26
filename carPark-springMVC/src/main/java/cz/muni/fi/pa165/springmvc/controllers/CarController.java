/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.enums.Fuel;
import cz.muni.fi.pa165.enums.Transmission;
import cz.muni.fi.pa165.facade.CarFacade;
import cz.muni.fi.pa165.springmvc.forms.CarCreateDTOValidator;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author xcmarko
 */
@Controller
@RequestMapping("/car")
public class CarController {
    
    final static Logger log = LoggerFactory.getLogger(CarController.class);
    
    @Autowired
    private CarFacade carFacade;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        
        return "car/index";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";
        
        CarDTO car = carFacade.getCarById(id);
        try{
            carFacade.deleteCar(id);
            log.debug("delete({})", id);
            redirectAttributes.addFlashAttribute("alert_success", "Car \"" + car.getName() + "\" was deleted.");
        } catch (JpaSystemException ex) {
            redirectAttributes.addFlashAttribute("alert_error", "Can not delete. Car \"" + car.getName() + "\" is used in Rental.");
        }
            
        return "redirect:" + uriBuilder.path("/car/list").toUriString();
    }
    
    @RequestMapping(value = {"/detail/{id}", "/detail/"}, method = RequestMethod.GET)
    public String detail(@PathVariable Optional<Long> id, Model model, HttpServletRequest request) {
        
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";

        if(!id.isPresent()) {
            model.addAttribute("car", new CarDTO());
        } else {
            model.addAttribute("car", carFacade.getCarById(id.get()));
        }
        return "car/detail";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("allCars", carFacade.getAllCars());
        return "car/list";
    }
    
    @ModelAttribute("transmissions")
    public List<Transmission> transmissions() {
        return new ArrayList<>(EnumSet.allOf(Transmission.class));
    }
    
    @ModelAttribute("fuels")
    public List<Fuel> fuels() {
        return new ArrayList<>(EnumSet.allOf(Fuel.class));
    }
    
    /**
     * Spring Validator added to JSR-303 Validator for this @Controller only.
     * It is useful  for custom validations that are not defined on the form bean by annotations.
     * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CarDTO) {
            binder.addValidators(new CarCreateDTOValidator());
        }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("car") CarDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";
        
        log.debug("create(car={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "car/detail";
        }
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create car
            id = carFacade.createCar(formBean);
        } else {
            //update car
            id = formBean.getId();
            carFacade.updateColor(id, formBean.getColor());
            carFacade.updateFuel(id, formBean.getFuel());
            carFacade.updateName(id, formBean.getName());
            carFacade.updateTransmission(id, formBean.getTransmission());
            updateOrCreate = "updated";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Car \"" + formBean.getName() + "\" was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/car/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
