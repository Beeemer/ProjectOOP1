import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Calendar {
    private List<Event> events;
    private Set<String> holidays;

    public Calendar() {
        events = new ArrayList<>();
        holidays = new HashSet<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(String date, String startTime, String endTime) {
        events.removeIf(event -> event.getDate().equals(date) &&
                event.getStartTime().equals(startTime) &&
                event.getEndTime().equals(endTime));
    }

    public List<Event> getEventsOnDate(String date) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                result.add(event);
            }
        }
        return result;
    }

    public void changeEvent(String date, String startTime, String option, String newValue) {
        for (Event event : events) {
            if (event.getDate().equals(date) && event.getStartTime().equals(startTime)) {
                switch (option) {
                    case "date":
                        event.setDate(newValue);
                        break;
                    case "starttime":
                        event.setStartTime(newValue);
                        break;
                    case "endtime":
                        event.setEndTime(newValue);
                        break;
                    case "name":
                        event.setName(newValue);
                        break;
                    case "note":
                        event.setNote(newValue);
                        break;
                }
            }
        }
    }

    public List<Event> findEvents(String searchString) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().contains(searchString) || event.getNote().contains(searchString)) {
                result.add(event);
            }
        }
        return result;
    }

    public void addHoliday(String date) {
        holidays.add(date);
    }

    public boolean isHoliday(String date) {
        return holidays.contains(date);
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(events);
            out.writeObject(holidays);
        }
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            events = (List<Event>) in.readObject();
            holidays = (Set<String>) in.readObject();
        }
    }
}

