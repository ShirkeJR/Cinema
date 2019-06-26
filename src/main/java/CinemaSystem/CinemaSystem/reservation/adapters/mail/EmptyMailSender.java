package CinemaSystem.CinemaSystem.reservation.adapters.mail;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;

public class EmptyMailSender implements MailSender {
    @Override
    public void sendMail(ShowReservation showReservation) {

    }
}
