package cinema.presentation.controllers;

import cinema.exceptions.exception.IllegalAccessRuntimeException;
import cinema.presentation.dtos.ErrorResponseDTO;
import cinema.presentation.dtos.StatsDTO;
import cinema.services.BookingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final BookingService bookingService;

    public AdminController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    // return number of seats available, total income and  number of sold seats
    @GetMapping("/stats")
    public ResponseEntity<StatsDTO> getStats(@RequestParam(name = "password") String password) {
        if (password == null) {
            throw new IllegalAccessRuntimeException("");
        }

        StatsDTO body = bookingService.getStats(password);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @ExceptionHandler({IllegalAccessRuntimeException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponseDTO> handleWrongPassword(Exception e) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                HttpStatus.UNAUTHORIZED,
                "The password is wrong!");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
