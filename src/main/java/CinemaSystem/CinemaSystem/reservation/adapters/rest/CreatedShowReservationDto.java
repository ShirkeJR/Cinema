package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.reservation.domain.Customer;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CreatedShowReservationDto {
    private String id;
    private Customer customer;
    private Set<Seat> reservedSeats;
    private Set<TicketOrder> tickets;
    private BigDecimal totalCost;
}
