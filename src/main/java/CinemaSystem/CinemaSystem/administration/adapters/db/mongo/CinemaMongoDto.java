package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cinemas")
public class CinemaMongoDto {

  @Id private String id;
  private String name;
  private String city;
}
