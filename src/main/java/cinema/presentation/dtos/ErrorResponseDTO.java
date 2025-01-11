package cinema.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ErrorResponseDTO {
    @JsonIgnore
    private int statusCode;
    private String error;

    public ErrorResponseDTO(HttpStatus httpStatus, String error) {
        statusCode = httpStatus.value();
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode.value();
    }
}
