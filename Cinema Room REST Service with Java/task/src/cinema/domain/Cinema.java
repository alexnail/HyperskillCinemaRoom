package cinema.domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {

    private int rows;
    private int columns;
    private final Map<Seat, Boolean> seats = new ConcurrentHashMap<>();

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        for (int row = 1 ; row <= rows ; row++) {
            for (int col = 1 ; col <= columns ; col++) {
                seats.put(new Seat(row, col), Boolean.TRUE);
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
        return seats.get(seat);
    }

    public Seat purchaseSeat(Seat seat) {
        seats.put(seat, Boolean.FALSE);
        return seat;
    }
}
