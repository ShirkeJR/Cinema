package CinemaSystem.CinemaSystem.reservation.domain.exceptions;

public class InvalidShowReservationStatusException extends RuntimeException {
  public InvalidShowReservationStatusException() {}

  public InvalidShowReservationStatusException(String message) {
    super(message);
  }
}
