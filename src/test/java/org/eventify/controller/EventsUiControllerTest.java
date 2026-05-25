package org.eventify.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventsUiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/admin/events/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("event-form"))
               .andExpect(model().attributeExists("registrationDTO"));
    }

    @Test
    public void testListEvents() throws Exception {
        mockMvc.perform(get("/admin/events"))
               .andExpect(status().isOk())
               .andExpect(view().name("event-list"))
               .andExpect(model().attributeExists("events"));
    }
}
