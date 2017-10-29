package loggers;

import entities.Event;
import org.springframework.stereotype.Component;

public class ConsoleEventLogger implements IEventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }
}
