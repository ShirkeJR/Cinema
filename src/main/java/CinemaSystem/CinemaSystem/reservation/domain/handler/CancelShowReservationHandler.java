package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.adapters.mail.MailSender;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import org.springframework.transaction.annotation.Transactional;

public class CancelShowReservationHandler implements Handler<CancelShowReservationCommand, String> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowRepository showRepository;
  private final MailSender mailSender;

  public CancelShowReservationHandler(
      ShowReservationRepository showReservationRepository, ShowRepository showRepository, MailSender mailSender) {
    this.showReservationRepository = showReservationRepository;
    this.showRepository = showRepository;
    this.mailSender = mailSender;
  }

  @Transactional
  @Override
  public String handle(CancelShowReservationCommand cmd) {
    var showReservation = showReservationRepository.get(cmd.reservationId);
    var show = showRepository.get(showReservation.getShowId());

    show.unReserveReservation(showReservation);
    showReservation.cancel();

    showRepository.put(show);
    showReservationRepository.put(showReservation);
    mailSender.sendMail(showReservation);
    return showReservation.getId();
  }
}
