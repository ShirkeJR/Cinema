package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Ticket {

  private String type;
  private int count;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return type.equals(ticket.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }
}
