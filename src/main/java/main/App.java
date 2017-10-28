package main;

import entities.Client;
import entities.Event;
import loggers.ConsoleEventLogger;
import loggers.IEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private IEventLogger eventLogger;
    private Event event;

    public App(Client client, IEventLogger eventLogger, Event event) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.event = event;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        App app = (App) applicationContext.getBean("app");

        app.logEvent("some event for user 1");

        app.logEvent("some event for user 2");

        app.eventLogger.logEvent(app.event);
        app.eventLogger.logEvent(app.event);

        app.eventLogger.logEvent(app.event);
        app.eventLogger.logEvent(app.event);
        app.eventLogger.logEvent(app.event);
        app.eventLogger.logEvent(app.event);
    }

    void logEvent(String msg){
        msg = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        //consoleEventLogger.logEvent(msg);
        System.out.println(msg);
    }
}
