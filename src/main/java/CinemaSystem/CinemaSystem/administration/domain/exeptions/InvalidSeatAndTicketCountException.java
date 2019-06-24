package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class InvalidSeatAndTicketCountException extends RuntimeException {
    public InvalidSeatAndTicketCountException(String message) {
        super(message);
    }

    public InvalidSeatAndTicketCountException() {
    }
}
