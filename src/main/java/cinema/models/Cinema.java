package cinema.models;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cinema {
    private final ConcurrentHashMap<Integer, Seat> seats;
    private final int numberOfRows = 9;
    private final int numberOfColumns = 9;

    public Cinema() {
        seats = new ConcurrentHashMap<>();
        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= numberOfColumns; j++) {
                // 0 <= i < numberOfRows 0 <= j < numberOfColumns, rows&columns are 1 indexed
                Seat seat = new Seat(i, j);
                seats.put(getSeatKey(i, j), seat);
            }
        }
    }

    public int getSeatKey(int row, int column) {
        return row * numberOfColumns + column;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public ConcurrentHashMap<Integer, Seat> getSeats() {
        return seats;
    }
    
}
