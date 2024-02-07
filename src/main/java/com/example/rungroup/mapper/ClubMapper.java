package com.example.rungroup.mapper;

import com.example.rungroup.dto.ClubDto;
import com.example.rungroup.models.Club;

import java.util.stream.Collectors;

import static com.example.rungroup.mapper.EventMapper.mapToEvent;
import static com.example.rungroup.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .Id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photoUrl(clubDto.getPhotoUrl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }
    public static ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .Id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();

        return clubDto;
    }
}
