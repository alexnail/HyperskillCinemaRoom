package cinema.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSeatException extends RuntimeException {

    public InvalidSeatException() {
        super("The number of a row or a column is out of bounds!");
    }
}
