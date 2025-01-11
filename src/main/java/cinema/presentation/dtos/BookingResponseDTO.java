package cinema.presentation.dtos;

import cinema.models.Ticket;

public class BookingResponseDTO {
    private final String token;
    private final Ticket ticket;

    public BookingResponseDTO(Ticket ticket) {
        this.token = ticket.getToken();
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Ticket getTicket() {
        return ticket;
    }
}