package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Ticket {
    @JsonIgnore
    private final String token;
    int row;
    int column;
    int price;

    public Ticket(int row, int column, int price) {
        token = UUID.randomUUID().toString();
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public String getToken() {
        return token;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
