package cinema.models;

import cinema.utils.SeatStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    @JsonIgnore
    private SeatStatus seatStatus;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        seatStatus = SeatStatus.AVAILABLE;
    }

    public int getRow() {
        return row;
    }


    public int getColumn() {
        return column;
    }

    public SeatStatus getSeatStatus(){
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getPrice() {
        int price;
        if (row <= 4) {
            price = 10;
        } else {
            price = 8;
        }
        return price;
    }
}
