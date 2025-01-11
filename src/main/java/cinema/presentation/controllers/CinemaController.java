package cinema.presentation.controllers;

import cinema.exceptions.exception.SeatNotFoundException;
import cinema.models.Ticket;
import cinema.presentation.dtos.*;
import cinema.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {
    private final BookingService bookingService;

    public CinemaController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/seats")
    public ResponseEntity<CinemaDTO> getCinemaInfo() {
        CinemaDTO cinemaInfo = bookingService.getCinemaInfo();
        return new ResponseEntity<>(cinemaInfo, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<BookingResponseDTO> buyTicket(@RequestBody BookingBody ticketInfo) {
        if (ticketInfo.row() < 1 || ticketInfo.column() < 1) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        BookingResponseDTO response = bookingService.bookSeat(ticketInfo.row(), ticketInfo.column());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<TicketDTO> returnTicket(@RequestBody ReturnBody requestBody) {
        Ticket ticket = bookingService.returnTicket(requestBody.token());
        TicketDTO body = new TicketDTO(ticket);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


}
