package loggers;

import entities.Event;

public class ConsoleEventLogger implements IEventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }
}
