package cinema.domain;

import java.util.UUID;

public class Ticket {
    private String token;
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(Seat seat) {
        this.ticket = seat;
        token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
