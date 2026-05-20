package org.eventify.Repository;

import org.eventify.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testSaveAndFindByNameContainingIgnoreCase() {
        // Arrange
        Event event1 = new Event(null, "Concierto de Rock", "Música");
        eventRepository.save(event1);

        Event event2 = new Event(null, "Concierto Sinfónico", "Música Clásica");
        eventRepository.save(event2);

        // Act
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Event> result = eventRepository.findByNameContainingIgnoreCase("concierto", pageable);

        // Assert
        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().stream().anyMatch(e -> e.getName().equals("Concierto de Rock")));
    }
}
