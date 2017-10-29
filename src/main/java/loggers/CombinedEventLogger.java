package loggers;

import entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
