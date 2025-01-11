package cinema.presentation.dtos;

import cinema.models.Ticket;

public class TicketDTO {
    private final Ticket ticket;

    public TicketDTO(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
