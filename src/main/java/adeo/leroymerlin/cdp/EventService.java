package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAll();
        // Filter the events list in pure JAVA here
        List<Event> filteredEvents = events.stream()
                                            .filter(event -> event.getBands().stream().anyMatch(band -> band.hasMemberWithNameContaining(query)))
                                            .collect(Collectors.toList());
        return filteredEvents;
    }

    public void updateEvent(Long eventId, Event eventReview) {
        Event event = eventRepository.findOne(eventId);
        if (event != null) {
            event.setComment(eventReview.getComment());
            event.setNbStars(eventReview.getNbStars());
            eventRepository.save(event);
        }

    }
}
