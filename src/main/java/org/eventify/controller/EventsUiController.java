package org.eventify.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.eventify.Service.EventService;
import org.eventify.Service.SiteService;
import org.eventify.dto.EventRegistrationDTO;
import org.eventify.models.Event;
import org.eventify.models.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Controller
@RequestMapping("/admin")
public class EventsUiController {

    private final EventService eventService;

    public EventsUiController(EventService eventService, SiteService siteService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/new")
    public String showRegistrationForm(Model model){
        model.addAttribute("registrationDTO", new EventRegistrationDTO());
        return "event-form";
    }

    @PostMapping("/events")
    public String registerEventsAndSite(@ModelAttribute("registrationDTO") EventRegistrationDTO dto){

        Site newSite = new Site();
        newSite.setSiteName(dto.getSiteName());
        newSite.setAddress(dto.getAddress());
        newSite.setCity(dto.getCity());

        Event newEvent = new Event();
        newEvent.setName(dto.getEventName());
        newEvent.setType(dto.getEventType());

        newEvent.setSite(newSite);

        eventService.create(newEvent);
        return "redirect:/admin/events/new?success";
    }

    @GetMapping("/events")
    public String listEvents(Model model){
        model.addAttribute("events", eventService.getAll(org.springframework.data.domain.PageRequest.of(0, 50)).getContent());

        return "event-list";
    }


}
