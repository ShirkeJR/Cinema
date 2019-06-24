package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class TicketOrder {

  private String type;
  private int count;
  private BigDecimal pricePerOne;
  private BigDecimal totalPrice;
}
