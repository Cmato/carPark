package cz.muni.fi.pa165.springmvc.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cz.muni.fi.pa165.dto.CreateReservationDTO;

/**
 *
 * @author xruzic16
 */
public class ReservationCreateDTOValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateReservationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateReservationDTO resDTO = (CreateReservationDTO) target;
    }
}
