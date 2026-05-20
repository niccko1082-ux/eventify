package org.eventify.controller;

import org.eventify.Service.EventService;
import org.eventify.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    void testGetAllEvents() throws Exception {
        // Arrange
        Event event1 = new Event(1L, "Concert Hall", "Music");
        Event event2 = new Event(2L, "Stadium", "Sports");
        Page<Event> eventPage = new PageImpl<>(Arrays.asList(event1, event2));
        when(eventService.getAll(any(Pageable.class))).thenReturn(eventPage);

        // Act & Assert
        mockMvc.perform(get("/event/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Concert Hall"));
    }

    @Test
    void testGetEventById() throws Exception {
        // Arrange
        Event event = new Event(1L, "Concert Hall", "Music");
        when(eventService.getByID(1L)).thenReturn(Optional.of(event));

        // Act & Assert
        mockMvc.perform(get("/event/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Concert Hall"))
                .andExpect(jsonPath("$.type").value("Music"));
    }
}
