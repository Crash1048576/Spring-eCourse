package loggers;

import entities.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() throws IOException {
        for(Event e : cache){
            super.logEvent(e);
        }
    }

    @PreDestroy
    private void destroy() throws IOException {
        if(!cache.isEmpty()){
            for(Event e : cache){
                super.logEvent(e);
            }
        }
    }
}
