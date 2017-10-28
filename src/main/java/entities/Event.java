package entities;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private Long id;
    private String message;
    private Date date;
    DateFormat dateFormat;

    private static long count = 1L;

    public Event(Date date, DateFormat dateFormat) {
        this.id = count++;
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
