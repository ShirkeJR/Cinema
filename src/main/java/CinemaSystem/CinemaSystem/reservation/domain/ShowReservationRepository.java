package CinemaSystem.CinemaSystem.reservation.domain;

public interface ShowReservationRepository {

  ShowReservation put(ShowReservation showReservation);

  ShowReservation get(String reservationId);
}
