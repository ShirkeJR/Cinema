package CinemaSystem.CinemaSystem;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.ParameterizedType;

@SpringBootApplication
@EnableScheduling
public class CinemaSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(CinemaSystemApplication.class, args);
  }

  @Bean
  public CommandGateway commandGateway(ApplicationContext applicationContext) {
    CommandGateway commandGateway = new CommandGateway();
    applicationContext
        .getBeansOfType(Handler.class)
        .forEach(
            (s, handler) -> {
              Class cmdClass =
                  (Class)
                      ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
                          .getActualTypeArguments()[0];
              commandGateway.registerHandler(cmdClass, handler);
            });
    return commandGateway;
  }
}
