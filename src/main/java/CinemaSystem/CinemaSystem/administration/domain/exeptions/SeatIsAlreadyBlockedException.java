package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class SeatIsAlreadyBlockedException extends RuntimeException {
  public SeatIsAlreadyBlockedException(String message) {
    super(message);
  }

  public SeatIsAlreadyBlockedException() {
  }
}
