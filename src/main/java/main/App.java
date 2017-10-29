package main;

import entities.Client;
import entities.Event;
import entities.EventType;
import loggers.CacheFileEventLogger;
import loggers.ConsoleEventLogger;
import loggers.IEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Component
public class App {
    @Autowired
    private Client client;

    @Resource(name = "cacheFileEventLogger")
    private IEventLogger defaultLogger;

    @Autowired
    private Map<EventType,IEventLogger> eventLoggers;

    @Autowired
    private Event event;

    public App(Client client, IEventLogger defaultLogger, Map<EventType, IEventLogger> eventLoggers, Event event) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggers = eventLoggers;
        this.event = event;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        App app = (App) applicationContext.getBean("app");

        app.logEvent(app.event, EventType.INFO);

        app.logEvent(app.event, EventType.ERROR);

        app.logEvent(app.event, null);
        System.out.println(app.client);
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
