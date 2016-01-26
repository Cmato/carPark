/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.controllers;

import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import static cz.muni.fi.pa165.springmvc.controllers.RentalController.log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.jpa.JpaSystemException;
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
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";
        
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
    public String detail(@PathVariable Optional<Long> id, Model model, HttpServletRequest request) {
        
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";

        if(!id.isPresent()) {
            model.addAttribute("employee", new EmployeeDTO());
        } else {
            model.addAttribute("employee", employeeFacade.findEmployeeById(id.get()));
        }
        return "employee/detail";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employee") EmployeeDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("authenticatedUser");
        if(user.getIsAdmin() == false)
            return "home/404";
        
        log.debug("create(employee={})", formBean);
        Long id = null;
        String updateOrCreate = "created";
        if(formBean.getId() == null) {
            //create employee
            try{
                id = employeeFacade.createEmployee(formBean);  
            } catch (ConstraintViolationException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "Employee with this e-mail already exists. ");
                return "redirect:" + uriBuilder.path("/employee/detail/").toUriString();
            } catch (MappingException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/employee/detail/").toUriString();
            } catch (IllegalArgumentException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. " + ex);
                return "redirect:" + uriBuilder.path("/employee/detail/").toUriString();
            }
        } else {
            //update employee
            try{
                id = formBean.getId();
                employeeFacade.updateEmployeeName(id, formBean.getName());
                employeeFacade.updateEmployeeBirth(id, formBean.getBirth());
                employeeFacade.updateEmployeeIdCardNumber(id, formBean.getIdCardNumber());
                employeeFacade.updateEmployeeEmail(id, formBean.getEmail());
                employeeFacade.updateEmployeePassword(id, formBean.getPassword());
                updateOrCreate = "updated";
            } catch (MappingException ex){
                log.warn("Error");
                redirectAttributes.addFlashAttribute("alert_error", "You have to fill all fields. ");
                return "redirect:" + uriBuilder.path("/employee/detail/{id}").buildAndExpand(id).encode().toUriString();
            }
        }
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Employee \"" + formBean.getName() + "\" was " + updateOrCreate);
        return "redirect:" + uriBuilder.path("/employee/list").toUriString();
    }
    
    
}
