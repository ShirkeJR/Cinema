package CinemaSystem.CinemaSystem.reservation.domain;

public interface ShowReservationRepository {

  void put(ShowReservation showReservation);

  ShowReservation get(String reservationId);
}
