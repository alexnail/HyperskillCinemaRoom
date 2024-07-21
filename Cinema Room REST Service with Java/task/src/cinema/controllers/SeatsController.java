package cinema.controllers;

import cinema.domain.Cinema;
import cinema.domain.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SeatsController {

    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getAllSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody Seat seatRequest) {
        if (isInvalidSeat(seatRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "The number of a row or a column is out of bounds!"));
        }

        if (cinema.isAvailable(seatRequest)) {
            return ResponseEntity.ok().body(cinema.purchaseSeat(seatRequest));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "The ticket has been already purchased!"));
        }
    }

    private boolean isInvalidSeat(Seat seatRequest) {
        return seatRequest.getRow() < 1 || seatRequest.getRow() > cinema.getRows() ||
                seatRequest.getColumn() < 1 || seatRequest.getColumn() > cinema.getColumns();
    }
}
