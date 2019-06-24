package CinemaSystem.CinemaSystem.reservation.domain.exceptions;

public class ShowReservationNotFoundException extends RuntimeException {
  public ShowReservationNotFoundException() {}

  public ShowReservationNotFoundException(String message) {
    super(message);
  }
}
