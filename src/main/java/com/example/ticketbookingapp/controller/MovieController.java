package com.example.ticketbookingapp.controller;

import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "app/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    ResponseEntity<List<Movie>> readMovies() {

        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Transactional
    ResponseEntity<Movie> createMovie(@RequestBody @Valid Movie toCreate) {
        Movie result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteMovie(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
