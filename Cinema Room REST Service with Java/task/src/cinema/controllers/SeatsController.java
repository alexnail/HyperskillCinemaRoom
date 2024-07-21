package cinema.controllers;

import cinema.controllers.exception.InvalidSeatException;
import cinema.controllers.exception.TicketUnavailableException;
import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.domain.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatsController {

    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getAllSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Ticket purchase(@RequestBody Seat seatRequest) {
        if (isInvalidSeat(seatRequest)) {
            throw new InvalidSeatException();
        }

        if (cinema.isAvailable(seatRequest)) {
            return cinema.purchaseSeat(seatRequest);
        } else {
            throw new TicketUnavailableException();
        }
    }

    private boolean isInvalidSeat(Seat seatRequest) {
        return seatRequest.getRow() < 1 || seatRequest.getRow() > cinema.getRows() ||
                seatRequest.getColumn() < 1 || seatRequest.getColumn() > cinema.getColumns();
    }
}
