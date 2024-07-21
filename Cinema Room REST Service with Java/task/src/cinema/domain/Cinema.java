package cinema.domain;

import cinema.exceptions.WrongTokenException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Cinema {

    private int rows;
    private int columns;

    private final Map<Seat, Ticket> seats = new ConcurrentHashMap<>();

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        for (int row = 1 ; row <= rows ; row++) {
            for (int col = 1 ; col <= columns ; col++) {
                seats.put(new Seat(row, col), new Ticket());
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats.keySet().stream().sorted().toList();
    }

    public boolean isAvailable(Seat seat) {
        return isNull(seats.get(seat).getToken());
    }

    public Ticket purchaseSeat(Seat seat) {
        Ticket ticket = new Ticket(seat);
        seats.put(seat, ticket);
        return ticket;
    }

    public Ticket returnTicket(String token) {
        Ticket purchased = seats.values().stream()
                .filter(ticket -> nonNull(ticket.getToken()) && ticket.getToken().equals(token))
                .findFirst().orElseThrow(WrongTokenException::new);

        purchased.setToken(null);
        seats.put(purchased.getTicket(), purchased);
        return purchased;
    }

    public Statistics collectStatistics() {
        Statistics statistics = new Statistics();
        statistics.setIncome(seats.values().stream()
                .filter(ticket -> nonNull(ticket.getToken()))
                .mapToLong(ticket -> ticket.getTicket().getPrice())
                .sum());
        statistics.setAvailable(seats.values().stream()
                .filter(ticket -> isNull(ticket.getToken()))
                .count());
        statistics.setPurchased(seats.values().stream()
                .filter(ticket -> nonNull(ticket.getToken()))
                .count());
        return statistics;
    }
}
