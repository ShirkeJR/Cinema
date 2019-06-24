package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class IllegalTicketTypeException extends RuntimeException {
  public IllegalTicketTypeException() {}

  public IllegalTicketTypeException(String message) {
    super(message);
  }
}
