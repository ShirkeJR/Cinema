package CinemaSystem.CinemaSystem.core;

import java.util.HashMap;
import java.util.Map;

public class CommandGateway {

  public Map<Class<? extends Command>, Handler> handlersMap = new HashMap<>();

  public <ReturnT> ReturnT execute(Command command) {
    Handler<Command, ReturnT> handler = handlerFor(command);
    return handler.handle(command);
  }

  private Handler handlerFor(Command command) {
    if (!handlersMap.containsKey(command.getClass())) {
      throw new IllegalArgumentException(
          String.format("No handler found for %s", command.getClass()));
    }
    return handlersMap.get(command.getClass());
  }

  public <CommandT extends Command> void registerHandler(
      Class<CommandT> commandClass, Handler<CommandT, ?> handler) {
    handlersMap.put(commandClass, handler);
  }
}
