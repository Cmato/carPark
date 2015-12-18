package cz.muni.fi.pa165.springmvc.forms;

import cz.muni.fi.pa165.dto.CarDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author xruzic16
 */
public class ReservationCreateDTOValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationDTO resDTO = (ReservationDTO) target;
        /*if (resDTO.getName() == null) return;
        if (resDTO.getTransmission() == null) return;
        if (resDTO.getFuel() == null) return;
        if (resDTO.getColor() == null) return;*/
    }
}
