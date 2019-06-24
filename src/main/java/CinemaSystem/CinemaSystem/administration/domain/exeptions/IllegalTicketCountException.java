package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class IllegalTicketCountException extends RuntimeException {
    public IllegalTicketCountException(String message) {
        super(message);
    }

    public IllegalTicketCountException() {
    }
}
