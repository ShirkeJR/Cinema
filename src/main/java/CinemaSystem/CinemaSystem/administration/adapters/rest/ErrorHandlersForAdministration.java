package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.CinemaNotFoundException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlersForAdministration {

  @ExceptionHandler(value = CinemaNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleCinemaNotFount() {}

  @ExceptionHandler(value = MovieNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleMovieNotFount() {}

  @ExceptionHandler(value = ShowNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleShowNotFount() {}
}
