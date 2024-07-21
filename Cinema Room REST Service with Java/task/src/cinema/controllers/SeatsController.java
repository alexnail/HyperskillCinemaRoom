package cinema.controllers;

import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.domain.Statistics;
import cinema.domain.Ticket;
import cinema.exceptions.InvalidSeatException;
import cinema.exceptions.TicketUnavailableException;
import cinema.exceptions.WrongPasswordException;
import cinema.services.CinemaService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatsController {

    private final CinemaService service;

    public SeatsController(CinemaService service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public Cinema getAllSeats() {
        return service.getAllSeats();
    }

    @PostMapping("/purchase")
    public Ticket purchase(@RequestBody Seat seatRequest) {
        if (service.isInvalidSeat(seatRequest)) {
            throw new InvalidSeatException();
        }

        if (service.isAvailable(seatRequest)) {
            return service.purchaseSeat(seatRequest);
        } else {
            throw new TicketUnavailableException();
        }
    }

    @PostMapping("/return")
    public Ticket returnTicket(@RequestBody Ticket ticketRequest) {
        return service.returnTicket(ticketRequest.getToken());
    }

    @GetMapping("/stats")
    public Statistics getStatistics(@RequestParam(required = false) String password) {
        if (!"super_secret".equals(password)) {
            throw new WrongPasswordException();
        }
        return service.getStatistics();
    }


}
