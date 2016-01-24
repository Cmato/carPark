/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author 
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        
        return "employee/index";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("allEmployees", employeeFacade.findAllEmployees());
        return "employee/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        EmployeeDTO employee = employeeFacade.findEmployeeById(id);
        try{
            employeeFacade.deleteEmployee(id);
            log.debug("delete({})", id);
            redirectAttributes.addFlashAttribute("alert_success", "Employee \"" + employee.getName() + "\" was deleted.");
        } catch (JpaSystemException ex) {
            redirectAttributes.addFlashAttribute("alert_error", "Can not delete. Employee \"" + employee.getName() + "\" is used in Rental.");
        }
            
        return "redirect:" + uriBuilder.path("/employee/list").toUriString();
    }
    
    @RequestMapping(value = {"/detail/{id}", "/detail/"}, method = RequestMethod.GET)
    public String detail(@PathVariable Optional<Long> id, Model model) {

        if(!id.isPresent()) {
            model.addAttribute("employee", new EmployeeDTO());
        } else {
            model.addAttribute("employee", employeeFacade.findEmployeeById(id.get()));
        }
        return "employee/detail";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employee") EmployeeDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(employee={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "employee/new";
        }
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create employee
            id = employeeFacade.createEmployee(formBean);
        } else {
            //update employee
            id = formBean.getId();
            employeeFacade.updateEmployeeName(id, formBean.getName());
            employeeFacade.updateEmployeeBirth(id, formBean.getBirth());
            employeeFacade.updateEmployeeIdCardNumber(id, formBean.getIdCardNumber());
            updateOrCreate = "updated";
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Employee \"" + formBean.getName() + "\" was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/employee/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
    
    
}
