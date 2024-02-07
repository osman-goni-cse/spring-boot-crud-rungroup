package com.example.rungroup.dto;

import com.example.rungroup.models.Club;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private Long Id;
    private String name;
    private String type;
    private String photoUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Club club;
}
