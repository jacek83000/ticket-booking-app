package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    void deleteById(Integer id);

    Optional<Seat> findById(Integer id);

    List<Seat> findAll();

    Seat save(Seat entity);
}
