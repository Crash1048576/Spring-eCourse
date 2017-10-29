package loggers;

import entities.Event;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public interface IEventLogger {
    void logEvent(Event event) throws IOException;
}
