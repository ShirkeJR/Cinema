package CinemaSystem.CinemaSystem.reservation.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;

import java.util.UUID;

public class CancelShowReservationCommand implements Command {

    public UUID showReservationID;
}
