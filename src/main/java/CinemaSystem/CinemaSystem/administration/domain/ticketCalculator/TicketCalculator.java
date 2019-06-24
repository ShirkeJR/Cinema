package CinemaSystem.CinemaSystem.administration.domain.ticketCalculator;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.IllegalTicketTypeException;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Component
public class TicketCalculator {

  public Set<TicketOrder> calculateTickets(Map<String, BigDecimal> ticketPrices, Set<Ticket> tickets) {
    Set<TicketOrder> ticketOrders = Sets.newHashSet();

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
