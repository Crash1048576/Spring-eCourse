package loggers;

import entities.Event;

import java.io.IOException;
import java.util.List;

public class CombinedEventLogger implements IEventLogger{
    List<IEventLogger> loggers;

    public CombinedEventLogger(List<IEventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for(IEventLogger logger : loggers){
            logger.logEvent(event);

        }
    }
}
