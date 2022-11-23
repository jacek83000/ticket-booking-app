package com.example.ticketbookingapp.controller;

import com.example.ticketbookingapp.model.DTO.MovieDTO;
import com.example.ticketbookingapp.model.Screening;
import com.example.ticketbookingapp.service.ScreeningService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "app/{date}/between:{start}&{end}/screenings")
public class ScreeningController {

    @Autowired
    private ScreeningService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{idScreening}")
    ResponseEntity<Screening> readScreening(@PathVariable int idScreening) {
        return service.findById(idScreening)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Transactional
    ResponseEntity<List<MovieDTO>> readScreenings(@PathVariable String date, @PathVariable int start, @PathVariable int end) {
        List<Screening> screenings = service.findByTimeBetween(getLocalDateTime(date, start), getLocalDateTime(date, end));

        Comparator<MovieDTO> compareByTitleAndScreeningTime = Comparator
                .comparing(MovieDTO::getTitle)
                .thenComparing(MovieDTO::getScreeningTime);

        return ResponseEntity.ok(
                screenings.stream().map(this::convertMovieToDTO).sorted(compareByTitleAndScreeningTime).collect(Collectors.toList()));
    }

    @PostMapping
    @Transactional
    ResponseEntity<Screening> createScreening(@RequestBody @Valid Screening toCreate) {
        Screening result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    LocalDateTime getLocalDateTime(String date, int hour){
        String[] splitDate = date.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);
        int minute = 0;

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    private MovieDTO convertMovieToDTO(Screening screening) {
        MovieDTO movieDTO = modelMapper.map(screening, MovieDTO.class);

        movieDTO.setTitle(screening.getMovie().getTitle());
        movieDTO.setScreeningTime(screening.getScreeningTimeAsString());

        return movieDTO;
    }
}
