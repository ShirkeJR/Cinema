package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "movies")
public class MovieMongoDto {

  @Id private String id;
  private String title;
  private String description;
}
