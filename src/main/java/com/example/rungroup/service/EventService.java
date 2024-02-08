package com.example.rungroup.service;

import com.example.rungroup.dto.EventDto;
import com.example.rungroup.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto getEventById(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEventById(Long eventId);
}
