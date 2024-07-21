package cinema.domain;

public class Seat implements Comparable<Seat> {
    private final int row;
    private final int column;

    public Seat() {
        this.row = 0;
        this.column = 0;
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return row <= 4 ? 10 : 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public int compareTo(Seat seat) {
        if (this.row != seat.row) {
            return Integer.compare(this.row, seat.row);
        }
        return Integer.compare(this.column, seat.column);
    }
}
