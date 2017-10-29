package loggers;

import entities.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

public class FileEventLogger implements IEventLogger {

    private String fileName;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, "\n"+event.toString(), true);
    }

    @PostConstruct
    private void init(){
        File file = new File(fileName);
        file.canWrite();
    }
}
