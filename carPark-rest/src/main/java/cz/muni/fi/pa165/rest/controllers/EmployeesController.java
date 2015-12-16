/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ErikHasprunár
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_EMPLOYEES)
public class EmployeesController {
    
    final static Logger logger = LoggerFactory.getLogger(EmployeesController.class);
    
    @Autowired
    private EmployeeFacade employeeFacade;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<EmployeeDTO> getUsers() throws JsonProcessingException {
        
        logger.debug("rest getEmployees()");
        return employeeFacade.findAllEmployees();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final EmployeeDTO getUser(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getEmployee({})", id);

        try {
            EmployeeDTO employeeDTO = employeeFacade.findEmployeeById(id);
            return employeeDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
}
