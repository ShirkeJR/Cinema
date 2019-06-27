package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {

  private String id;
  private String name;
  private String city;

  public static Cinema of(String id, CreateCinemaCommand cmd){
    return new Cinema(id, cmd.name, cmd.city);
  }
}
