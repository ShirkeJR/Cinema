package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.IllegalTicketTypeException;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketCalculatorTest {

  private final Map<String, BigDecimal> ticketPrices =
      Map.of(
          "normal", new BigDecimal(20),
          "extra", new BigDecimal(30));
  private final Set<Ticket> tickets = Set.of(new Ticket("normal", 2), new Ticket("extra", 1));

  private final TicketCalculator ticketCalculator = new TicketCalculator();

  @Test
  void returnsTwoTicketOrders() {
    var countOfTheSameTickets = 2;
    var countOfTickets = 3;
    var totalCostForTickets = new BigDecimal(70);

    Set<TicketOrder> ticketOrders = ticketCalculator.calculateTickets(ticketPrices, tickets);

    assertThat(ticketOrders).hasSize(countOfTheSameTickets);
    assertThat(sumTickets(ticketOrders)).isEqualTo(countOfTickets);
    assertThat(getTotalCostOfTickets(ticketOrders)).isEqualTo(totalCostForTickets);
  }

  private BigDecimal getTotalCostOfTickets(Set<TicketOrder> ticketOrders) {
    return ticketOrders.stream().map(TicketOrder::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private int sumTickets(Set<TicketOrder> ticketOrders) {
    return ticketOrders.stream().map(TicketOrder::getCount).reduce(Integer::sum).get();
  }

  @Test
  void expectsIllegalTicketTypeExceptionWhenShowDoesntContainTicketType() {
    var wrongTickets = Set.of(new Ticket("student", 2));

    Executable executable = () -> ticketCalculator.calculateTickets(ticketPrices, wrongTickets);

    assertThrows(IllegalTicketTypeException.class, executable);
  }
}
