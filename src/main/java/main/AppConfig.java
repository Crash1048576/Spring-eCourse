package main;

import entities.Event;
import entities.EventType;
import loggers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.DateFormat;
import java.util.*;

@Configuration
@ComponentScan("classpath: entities, main")
public class AppConfig {

    @Autowired
    FileEventLogger fileEventLogger;
    @Autowired
    ConsoleEventLogger consoleEventLogger;
    @Autowired
    CombinedEventLogger combinedEventLogger;

    @Bean()
    public Event event(){
         return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @Bean
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }
    @Bean
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger("spring.txt");
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger(){
        return new CacheFileEventLogger("spring.txt", 5);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger(){
        List<IEventLogger> loggers = new ArrayList<>();

        loggers.add(fileEventLogger);
        loggers.add(consoleEventLogger);

        return new CombinedEventLogger(loggers);
    }

    @Bean
    public Map<EventType, IEventLogger> eventLoggers(){
        Map<EventType, IEventLogger> result = new HashMap<>();

        result.put(EventType.INFO, consoleEventLogger);
        result.put(EventType.ERROR,combinedEventLogger);

        return result;
    }
}

