package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findById(Integer id);

    List<Reservation> findAll();

    Reservation save(Reservation entity);
}
