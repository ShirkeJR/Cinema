package CinemaSystem.CinemaSystem.core;

public interface Handler<CommandT extends Command, ReturnT>  {

    ReturnT handle(CommandT command);
}
