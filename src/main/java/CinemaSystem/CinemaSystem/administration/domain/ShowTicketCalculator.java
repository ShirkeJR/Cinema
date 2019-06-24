package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.IllegalTicketTypeException;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ShowTicketCalculator {

  public Set<TicketOrder> calculateTickets(Map<String, BigDecimal> ticketPrices, List<Ticket> tickets) {
    Set<TicketOrder> ticketOrders = new HashSet<>();

    for (Ticket ticket : tickets) {
      if (!ticketPrices.containsKey(ticket.getType())) {
        throw new IllegalTicketTypeException("Wrong ticket");
      }
      var pricePerTicket = ticketPrices.get(ticket.getType());
      var ticketOrder =
          TicketOrder.builder()
              .type(ticket.getType())
              .count(ticket.getCount())
              .pricePerOne(pricePerTicket)
              .totalPrice(pricePerTicket.multiply(new BigDecimal(ticket.getCount())))
              .build();
      ticketOrders.add(ticketOrder);
    }
    return ticketOrders;
  }
}
