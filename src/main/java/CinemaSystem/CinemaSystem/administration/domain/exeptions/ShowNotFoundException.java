package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class ShowNotFoundException extends RuntimeException {
  public ShowNotFoundException() {}

  public ShowNotFoundException(String message) {
    super(message);
  }
}
