package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Movie;

import java.util.List;

public interface MovieService {
    void deleteById(Integer id);

    List<Movie> findAll();

    Movie save(Movie entity);
}
