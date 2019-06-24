package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Builder
public class TicketOrder {

  private String type;
  private int count;
  private BigDecimal pricePerOne;
  private BigDecimal totalPrice;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TicketOrder that = (TicketOrder) o;
    return type.equals(that.type) &&
            pricePerOne.equals(that.pricePerOne);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, pricePerOne);
  }
}
