package CinemaSystem.CinemaSystem.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandGatewayTest {

  @Mock private CommandGateway gateway;

  private TestHandler handler = new TestHandler();
  private TestCommand command = new TestCommand();

  @BeforeEach
  void setUp() {
    gateway = new CommandGateway();
  }

  @Test
  public void handlesCommand() {
    gateway.registerHandler(TestCommand.class, handler);

    gateway.execute(command);

    assertThat(handler.isHandled()).isTrue();
  }
}
