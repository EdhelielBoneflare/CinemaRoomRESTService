package cinema.services;

import cinema.exceptions.exception.IllegalAccessRuntimeException;
import cinema.exceptions.exception.SeatNotAvailableException;
import cinema.exceptions.exception.SeatNotFoundException;
import cinema.exceptions.exception.TicketNotFoundException;
import cinema.models.Cinema;
import cinema.models.Seat;
import cinema.models.Ticket;
import cinema.presentation.dtos.BookingResponseDTO;
import cinema.presentation.dtos.CinemaDTO;
import cinema.presentation.dtos.StatsDTO;
import cinema.utils.SeatStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {
    private static final String ADMIN_PASSWORD = "super_secret";

    private final Cinema cinema;
    private final ConcurrentHashMap<String, Ticket> tickets;

    public BookingService(Cinema cinema) {
        this.cinema = cinema;
        tickets = new ConcurrentHashMap<>();
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : cinema.getSeats().values()) {
            if (seat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public CinemaDTO getCinemaInfo() {
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setNumberOfRows(cinema.getNumberOfRows());
        cinemaDTO.setNumberOfColumns(cinema.getNumberOfColumns());
        cinemaDTO.setAvailableSeats(getAvailableSeats());
        return cinemaDTO;
    }

    public BookingResponseDTO bookSeat(int row, int column) throws SeatNotFoundException, SeatNotAvailableException {
        if (row > cinema.getNumberOfRows() || column > cinema.getNumberOfColumns()) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        int seatKey = cinema.getSeatKey(row, column);
        Seat seat = cinema.getSeats().get(seatKey);
        
        if (seat == null) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }
        
        synchronized (seat) {
            if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
                seat.setSeatStatus(SeatStatus.TAKEN);
                Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice());
                tickets.put(ticket.getToken(), ticket);
                return new BookingResponseDTO(ticket);
            } else {
                throw new SeatNotAvailableException("The ticket has been already purchased!");
            }
        }
    }

    public Ticket returnTicket(String token) throws TicketNotFoundException {
        Ticket ticket = tickets.get(token);
        if (ticket == null) {
            throw new TicketNotFoundException("Wrong token!");
        }
        tickets.remove(token);

        int seatKey = cinema.getSeatKey(ticket.getRow(), ticket.getColumn());
        Seat seat = cinema.getSeats().get(seatKey);

        synchronized (seat) {
            seat.setSeatStatus(SeatStatus.AVAILABLE);
        }

        return ticket;
    }

    public StatsDTO getStats(String password) throws IllegalAccessRuntimeException {
        if (!password.equals(ADMIN_PASSWORD)) {
            throw new IllegalAccessRuntimeException("The password is wrong!");
        }

        int totalIncome = 0;
        int numberOfAvailableSeats = getAvailableSeats().size();
        int purchased = tickets.size();

        for (Ticket ticket : tickets.values()) {
            totalIncome += ticket.getPrice();
        }


        return new StatsDTO(totalIncome, numberOfAvailableSeats, purchased);
    }
}
