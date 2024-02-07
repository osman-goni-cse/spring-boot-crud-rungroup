package com.example.rungroup.repository;

import com.example.rungroup.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String url); // this works because of naming convention findByPropertyName
    @Query("SELECT tab FROM Club as tab WHERE tab.title LIKE CONCAT('%', :query, '%')")
    List<Club> searchClubByTitle(String query);
}
