package CinemaSystem.CinemaSystem.reservation.adapters.scheduler;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelExpiredShowReservationCommand;
import org.springframework.scheduling.annotation.Scheduled;

public class ShowReservationCancelingScheduler {

    private final CommandGateway commandGateway;

    public ShowReservationCancelingScheduler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Scheduled(fixedRate = 60 * 1000, initialDelay = 60 * 1000) // ms
    public void cancelExpiredReservations() {
        commandGateway.execute(new CancelExpiredShowReservationCommand());
    }
}
