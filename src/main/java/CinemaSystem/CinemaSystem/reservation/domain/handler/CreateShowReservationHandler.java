package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.adapters.mail.MailSender;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;

import java.util.Set;

public class CreateShowReservationHandler implements Handler<CreateShowReservationCommand, ShowReservation> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowReservationFactory showReservationFactory;
  private final ShowRepository showRepository;
  private final MailSender mailSender;

  public CreateShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository, MailSender mailSender) {

    this.showReservationRepository = showReservationRepository;
    this.showReservationFactory = showReservationFactory;
    this.showRepository = showRepository;
    this.mailSender = mailSender;
  }

  @Override
  public ShowReservation handle(CreateShowReservationCommand cmd) {
    var show = showRepository.get(cmd.showId);

    Set<TicketOrder> ticketOrders =
        show.calculateTicketsPerSeats(cmd.tickets, cmd.reservedSeats.size());

    var showReservation = showReservationFactory.create(cmd, ticketOrders);
    showReservationRepository.put(showReservation);

    show.reserveReservation(showReservation);
    showRepository.put(show);
    mailSender.sendMail(showReservation);
    return showReservation;
  }
}
