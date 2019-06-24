package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class MovieNotFoundException extends RuntimeException {
  public MovieNotFoundException() {}

  public MovieNotFoundException(String message) {
    super(message);
  }
}
