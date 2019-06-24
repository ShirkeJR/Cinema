package CinemaSystem.CinemaSystem.administration.domain.exeptions;

public class CinemaNotFoundException extends RuntimeException {
    public CinemaNotFoundException() {
    }

    public CinemaNotFoundException(String message) {
        super(message);
    }
}
