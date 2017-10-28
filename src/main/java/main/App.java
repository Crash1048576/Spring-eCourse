package main;

import entities.Client;
import entities.Event;
import entities.EventType;
import loggers.CacheFileEventLogger;
import loggers.ConsoleEventLogger;
import loggers.IEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private IEventLogger defaultLogger;
    private Map<EventType,IEventLogger> eventLoggers;
    private Event event;

    public App(Client client, IEventLogger defaultLogger, Map<EventType, IEventLogger> eventLoggers, Event event) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggers = eventLoggers;
        this.event = event;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        applicationContext.registerShutdownHook();

        App app = (App) applicationContext.getBean("app");

        app.logEvent(app.event, EventType.INFO);

        app.logEvent(app.event, EventType.ERROR);

        app.logEvent(app.event, null);

    }

    public void logEvent(Event event, EventType eventType) throws IOException {

        IEventLogger logger = eventLoggers.get(eventType);
        if(logger == null){
            defaultLogger.logEvent(event);
        }else{
            logger.logEvent(event);
        }
    }
}
