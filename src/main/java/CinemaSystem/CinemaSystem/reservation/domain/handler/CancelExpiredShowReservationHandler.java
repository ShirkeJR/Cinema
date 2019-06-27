package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.adapters.mail.MailSender;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelExpiredShowReservationCommand;

import java.time.LocalDateTime;
import java.util.List;

public class CancelExpiredShowReservationHandler
    implements Handler<CancelExpiredShowReservationCommand, Void> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowRepository showRepository;
  private final MailSender mailSender;

  public CancelExpiredShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowRepository showRepository,
      MailSender mailSender) {
    this.showReservationRepository = showReservationRepository;
    this.showRepository = showRepository;
    this.mailSender = mailSender;
  }

  @Override
  public Void handle(CancelExpiredShowReservationCommand command) {
    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);
    List<Show> toExpire = showRepository.findAllByTime(expirationTime);
    toExpire.forEach(
        show -> {
          show.cancelNotPayedReservations();
          showRepository.put(show);
        });
    return null;
  }
}
