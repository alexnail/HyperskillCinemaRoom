package cinema.services;

import cinema.domain.Cinema;
import cinema.domain.Seat;
import cinema.domain.Statistics;
import cinema.domain.Ticket;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final Cinema cinema = new Cinema(9, 9);

    public Cinema getAllSeats() {
        return cinema;
    }

    public boolean isAvailable(Seat seatRequest) {
        return cinema.isAvailable(seatRequest);
    }

    public Ticket purchaseSeat(Seat seatRequest) {
        return cinema.purchaseSeat(seatRequest);
    }

    public Ticket returnTicket(String token) {
        return cinema.returnTicket(token);
    }

    public Statistics getStatistics() {
        return cinema.collectStatistics();
    }

    public boolean isInvalidSeat(Seat seatRequest) {
        return seatRequest.getRow() < 1 || seatRequest.getRow() > cinema.getRows() ||
                seatRequest.getColumn() < 1 || seatRequest.getColumn() > cinema.getColumns();
    }
}
