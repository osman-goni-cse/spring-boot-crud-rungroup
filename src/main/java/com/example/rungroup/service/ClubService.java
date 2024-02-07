package com.example.rungroup.service;

import com.example.rungroup.dto.ClubDto;
import com.example.rungroup.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);

    void editClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void deleteClub(Long clubId);
    List<ClubDto> searchClub(String query);
}
