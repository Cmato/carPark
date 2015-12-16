package cz.muni.fi.pa165.exceptions;

/**
 *
 * @author xhubeny2
 */
@SuppressWarnings("serial")
public class CarParkServiceException extends RuntimeException{
   
    public CarParkServiceException() {
        super();
    }

    public CarParkServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CarParkServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarParkServiceException(String message) {
        super(message);
    }

    public CarParkServiceException(Throwable cause) {
        super(cause);
    }
}
