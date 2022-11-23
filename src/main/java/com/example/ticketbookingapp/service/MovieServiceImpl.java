package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.model.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository repository;

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @Override
    public Movie save(Movie entity) {
        return repository.save(entity);
    }
}
