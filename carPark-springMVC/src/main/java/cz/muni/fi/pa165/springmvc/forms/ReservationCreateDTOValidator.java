package cz.muni.fi.pa165.springmvc.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cz.muni.fi.pa165.dto.ReservationDTO;

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
        if (resDTO.getCar() == null) return;
    }
}
