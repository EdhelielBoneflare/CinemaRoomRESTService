package cinema.presentation.dtos;

import cinema.models.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CinemaDTO {
    @JsonProperty("rows")
    private int numberOfRows;
    @JsonProperty("columns")
    private int numberOfColumns;
    @JsonProperty("seats")
    private List<Seat> availableSeats;

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
