package com.example.rungroup.controller;

import com.example.rungroup.dto.EventDto;
import com.example.rungroup.models.Club;
import com.example.rungroup.models.Event;
import com.example.rungroup.service.ClubService;
import com.example.rungroup.service.EventService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private ClubService clubService;
    private EventService eventService;
    @Autowired
    public EventController(ClubService clubService, EventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);

        return "create-event-form";
    }
    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @ModelAttribute("event") EventDto eventDto,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("eventDto", eventDto);
            return "";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events")
    public String showAllEvents(Model model) {
        List<EventDto> eventDtos = eventService.findAllEvents();
        model.addAttribute("events", eventDtos);
        return "event-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetails(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.getEventById(eventId);
        model.addAttribute("event", eventDto);
        return "event-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String eventUpdateForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.getEventById(eventId);
        model.addAttribute("event", eventDto);
        return "update-event-form";
    }

    @PostMapping("/events/{eventId}/edit")
    public String eventUpdate(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "event-detail";
        }
        EventDto eventDto1 = eventService.getEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(eventDto1.getClub());

        eventService.updateEvent(eventDto);
        model.addAttribute("event", eventDto);

        return "event-detail";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }
}
