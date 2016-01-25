package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="Resource not found or can't be deleted due to linkage to rentals or reservations.")
public class ResourceNotFoundException extends RuntimeException {
    
} 
