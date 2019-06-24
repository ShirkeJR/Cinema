package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlersForReservation {

    @ExceptionHandler(value = IllegalTicketCountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIllegalTicketCountException() { }

    @ExceptionHandler(value = InvalidSeatAndTicketCountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidSeatAndTicketCountException() { }

    @ExceptionHandler(value = SeatIsAlreadyBlockedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleSeatIsAlreadyBlockedException() { }

    @ExceptionHandler(value = CinemaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCinemaNotFount() { }

    @ExceptionHandler(value = MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleMovieNotFount() { }

    @ExceptionHandler(value = ShowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleShowNotFount() { }

    @ExceptionHandler(value = IllegalTicketTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIllegalTicketTypeException() { }

    @ExceptionHandler(value = InvalidSeatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleInvalidSeatException() { }
}