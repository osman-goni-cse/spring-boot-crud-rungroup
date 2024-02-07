package com.example.rungroup.controller;

import com.example.rungroup.dto.ClubDto;
import com.example.rungroup.dto.EventDto;
import com.example.rungroup.models.Club;
import com.example.rungroup.models.Event;
import com.example.rungroup.service.impl.ClubServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class ClubController {

    private ClubServiceImpl clubService;
    @Autowired
    public ClubController(ClubServiceImpl clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
    @GetMapping("/clubs/{clubId}")
    public String viewClubs(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        List<EventDto> events = clubDto.getEvents();
        model.addAttribute("club", clubDto);
        model.addAttribute("events", events);
        return "club-view";
    }

    @GetMapping("/clubs/new")
    public String createClubs(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "create-club";
    }

    @PostMapping("/clubs/new")
    public String createClubs(@Valid @ModelAttribute("club") ClubDto clubDto,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "create-club";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId,
                           Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult bindingResult,
                           Model model) {
        clubDto.setId(clubId);


        if (bindingResult.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-edit";
        }
        clubService.editClub(clubDto);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(name = "query") String query, Model model) {
        List<ClubDto> clubDtos = clubService.searchClub(query);
        model.addAttribute("clubs", clubDtos);
        return "clubs-list";
    }
}
