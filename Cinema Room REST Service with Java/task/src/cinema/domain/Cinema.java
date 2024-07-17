package cinema.domain;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int rows;
    private int columns;
    private List<Seat> seats = new ArrayList<>();

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        for (int row = 1 ; row <= rows ; row++) {
            for (int col = 1 ; col <= columns ; col++) {
                seats.add(new Seat(row, col));
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
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
