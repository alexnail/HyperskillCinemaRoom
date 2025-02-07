package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TicketUnavailableException extends RuntimeException {
    public TicketUnavailableException() {
        super("The ticket has been already purchased!");
    }
}
