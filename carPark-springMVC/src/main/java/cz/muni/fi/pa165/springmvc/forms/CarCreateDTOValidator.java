/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.springmvc.forms;

import cz.muni.fi.pa165.dto.CarDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author xcmarko
 */
public class CarCreateDTOValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CarDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarDTO carDTO = (CarDTO) target;
        if (carDTO.getName() == null) return;
        if (carDTO.getTransmission() == null) return;
        if (carDTO.getFuel() == null) return;
        if (carDTO.getColor() == null) return;
    }
}
