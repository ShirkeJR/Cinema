package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Ticket {

  private String type;
  private int count;

  private Ticket(String type, int count) {
    this.type = type;
    this.count = count;
  }

  public static Ticket of(String type, int count) {
    return new Ticket(type, count);
  }

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
