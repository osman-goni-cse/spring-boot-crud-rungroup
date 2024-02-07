package com.example.rungroup.service;

import com.example.rungroup.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();
}
