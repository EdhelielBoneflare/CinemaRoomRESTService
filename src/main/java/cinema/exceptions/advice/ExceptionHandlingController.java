package cinema.exceptions.advice;

import cinema.exceptions.exception.SeatNotAvailableException;
import cinema.exceptions.exception.SeatNotFoundException;
import cinema.exceptions.exception.TicketNotFoundException;
import cinema.presentation.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorResponseDTO> handleSeatNotAvailable(SeatNotAvailableException e) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST,
                e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SeatNotFoundException.class, TicketNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNotFound(RuntimeException e) {
        ErrorResponseDTO body = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST,
                e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
