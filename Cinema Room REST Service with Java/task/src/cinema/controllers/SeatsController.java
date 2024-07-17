package cinema.controllers;

import cinema.domain.Cinema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatsController {

    @GetMapping
    public Cinema getAllSeats() {
        return new Cinema(9, 9);
    }
}
