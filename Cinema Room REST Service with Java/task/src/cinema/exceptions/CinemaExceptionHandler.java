package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CinemaExceptionHandler {

    @ExceptionHandler({InvalidSeatException.class, TicketUnavailableException.class, WrongTokenException.class})
    public ResponseEntity<CinemaErrorBody> handleException(RuntimeException re) {
        CinemaErrorBody body = new CinemaErrorBody(re.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public record CinemaErrorBody(String error) {}
}
