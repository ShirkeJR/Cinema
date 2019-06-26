package CinemaSystem.CinemaSystem.reservation.adapters.mail;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;

public interface MailSender {
    void sendMail(ShowReservation showReservation);
}
