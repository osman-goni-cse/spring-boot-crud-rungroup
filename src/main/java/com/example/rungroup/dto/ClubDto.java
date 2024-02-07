package com.example.rungroup.dto;

import com.example.rungroup.models.Event;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ClubDto {
    private Long Id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Photo Url should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private List<EventDto> events;
}
