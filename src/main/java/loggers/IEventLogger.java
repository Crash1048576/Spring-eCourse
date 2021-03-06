package loggers;

import entities.Event;

import java.io.IOException;

public interface IEventLogger {
    void logEvent(Event event) throws IOException;
}
